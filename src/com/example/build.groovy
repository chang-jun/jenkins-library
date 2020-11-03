#!/usr/bin/env groovy
package com.example

void goBuild() {
    color.green('>>>>>>>>>>>> Build Go <<<<<<<<<<<<')
    // sh ('''
    // go version
    // go mod tidy -v
    // go build -o app
    // ''')

    sh ('''
    go version
    ''')
}
