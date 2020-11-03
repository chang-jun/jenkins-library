#!/usr/bin/env groovy

void call(Map map) {
    // Public Arguments
    String k8sCloud
    switch (map.project_env) {
        case 'develop':
            k8sCloud = 'kubernetes-develop'
            break
        case 'testing':
            k8sCloud = 'kubernetes-testing'
            break
        case 'staging':
            k8sCloud = 'kubernetes-staging'
            break
        case 'production':
            k8sCloud = 'kubernetes-production'
            break
    }

    map.put('k8s_cloud', k8sCloud)

    goPipeline(map)
}
