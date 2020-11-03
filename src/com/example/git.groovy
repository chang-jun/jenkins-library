#!/usr/bin/env groovy
package com.example

void checkoutBranche(String gitBranch, String gitSshKey, String gitUrl) {
    color.green('>>>>>>>>>>>> Git Pull Config <<<<<<<<<<<<')

    checkout([
        $class: 'GitSCM',
        branches: [[name: gitBranch]],
        userRemoteConfigs: [[credentialsId: gitSshKey, url: gitUrl]]
    ])
}
