#!/usr/bin/env groovy
import com.example.build
import com.example.git
import com.example.deploy
import com.example.sonar

void call(Map map) {
    Object build = new build()
    Object git = new git()
    Object deploy = new deploy()
    Object sonar = new sonar()

    // Registry
    String registryServer = "${map.registry_server}"
    String registryGroup = "${map.registry_group}"
    String registryApp = "${map.registry_app}"
    String image = "${registryGroup}/${registryApp}"

    // Project
    String projectEnv = "${map.project_env}"

    pipeline {
        agent {
            kubernetes {
                cloud "${map.k8s_cloud}"
                yaml goPodYaml(map)
            }
        }

        options {
            disableConcurrentBuilds()
            timeout(time: 1, unit: 'HOURS')
            ansiColor('xterm')
            buildDiscarder(logRotator(numToKeepStr: '10'))
        }

        parameters {
            choice(name: 'CHOICE', choices: ['Deploy', 'Rollback'], description: '发布/回滚')
            choice(name: 'SCAN', choices: ['False', 'True'], description: '代码扫描')
            imageTag(name: 'IMAGE',
                    description: '请选择要回滚的镜像',
                    image: "${image}",
                    filter: ".*-${projectEnv}",
                    registry: "https://${registryServer}",
                    credentialId: 'docker-registry',
                    tagOrder: 'DSC_VERSION')
            gitParameter(branch: '',
                    branchFilter: 'origin/(.*)',
                    tagFilter: '*',
                    defaultValue: 'main',
                    description: '请选择要发布的版本',
                    name: 'BRANCH',
                    selectedValue: 'NONE',
                    sortMode: 'DESCENDING_SMART',
                    type: 'PT_BRANCH_TAG',
                    useRepository: "${map.git_url}",
                    listSize: '10')
        }

        stages {
            stage('Git') {
                steps {
                    script {
                        git.checkoutBranche("${map.git_branch}", 'git-ssh-key', "${map.git_url}")
                    }
                }
            }

            stage('Scan') {
                steps {
                    container('sonarqube') {
                        script {
                            sonar.scan('changjun', 'go-test', 'master')
                        }
                    }
                }
            }

            stage('Build') {
                steps {
                    container('golang') {
                        script {
                            build.goBuild()
                        }
                    }
                }
            }

            stage('Config') {
                steps {
                    container('golang') {
                        script {
                            build.goBuild()
                        }
                    }
                }
            }

            stage('Deploy') {
                steps {
                    container('kubectl') {
                        script {
                            deploy.k8s('default', 'zhaochangjun/go-api')
                        }
                    }
                }
            }
        }
    }
}
