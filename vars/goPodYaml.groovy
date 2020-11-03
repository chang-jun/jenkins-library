#!/usr/bin/env groovy

Object call(Map map) {
    String golangImageTag = '1.14'
    String exectorImageTag = 'debug-v1.3.0'
    String sonarqubeImageTag = '4.3.0'
    String kubectlImageTag = '1.18.2'

    if (map.containsKey('golang_image_tag')) {
        golangImageTag = map.golang_image_tag
    }

    if (map.containsKey('kubectl_image_tag')) {
        kubectlImageTag = map.kubectl_image_tag
    }

    String template = """
apiVersion: v1
kind: Pod
metadata:
  labels:
    app: go-deploy-pod
spec:
  containers:
    # sonarqube
    - name: sonarqube
      image: docker.io/zhaochangjun/sonar-scanner:${sonarqubeImageTag}
      # imagePullPolicy: Always
      command:
        - cat
      tty: true
      workingDir: /home/jenkins/agent
    # golang
    - name: golang
      image: docker.io/zhaochangjun/golang:${golangImageTag}
      # imagePullPolicy: Always
      command:
        - cat
      tty: true
      workingDir: /home/jenkins/agent
    # kaniko
    - name: kaniko
      image: docker.io/zhaochangjun/executor:${exectorImageTag}
      # imagePullPolicy: Always
      env:
        - name: "DOCKER_CONFIG"
          value: "/home/jenkins/agent/.docker"
      command:
        - /busybox/cat
      tty: true
      workingDir: /home/jenkins/agent
    # kubectl
    - name: kubectl
      image: docker.io/zhaochangjun/kubectl:${kubectlImageTag}
      # imagePullPolicy: Always
      env:
        - name: "KUBECONFIG"
          value: "/home/jenkins/agent/.kube/config"
      command:
        - cat
      tty: true
      workingDir: /home/jenkins/agent
"""

    return template
}
