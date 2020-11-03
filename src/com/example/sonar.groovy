#!/usr/bin/env groovy
package com.example

void scan(String gitGroup, String gitApp, String gitBranch) {
    color.green('>>>>>>>>>>>> Scan Code <<<<<<<<<<<<')

    // sh("""
    // sonar-scanner   -Dsonar.projectKey="${gitGroup}:${gitApp}" \
    //                 -Dsonar.projectName="${gitGroup}:${gitApp}" \
    //                 -Dsonar.projectVersion="${gitBranch}" \
    //                 -Dsonar.ws.timeout=30 \
    //                 -Dsonar.projectDescription="test project" \
    //                 -Dsonar.links.homepage=http://www.baidu.com\
    //                 -Dsonar.sources="." \
    //                 -Dsonar.sourceEncoding=UTF-8 \
    //                 -Dsonar.exclusions=**/*_test.go,**/vendor/**,**/vendors/**,**/secret.yaml,**/conf.ini \
    //                 -Dsonar.test.inclusions=**/*_test.go \
    //                 -Dsonar.test.exclusions=**/vendor/**
    // """)

    println('code scan')
    println(gitGroup)
    println(gitApp)
    println(gitBranch)
}
