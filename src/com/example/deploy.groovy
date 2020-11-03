#!/usr/bin/env groovy
package com.example

void k8s(String namespace, String image) {
    color.green('>>>>>>>>>>>> Deploy to Kubernetes <<<<<<<<<<<<')

    // sh ("""
    // kubectl version
    // kustomize edit set image app=${image}
    // kubectl apply -k overlays/${namespace}
    // kubewatcher overlays/${namespace}
    // """)

    println(namespace)
    println(image)

    sh 'kubectl version'
}
