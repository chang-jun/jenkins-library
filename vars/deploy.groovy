#!/usr/bin/env groovy

void call(Map map) {
    // Public config
    map.put('registry_server', 'registry.hub.docker.com')

    switch (map.git_app_language) {
        case 'html':
            htmlDeploy(map)
            break
        case 'php':
            phpDeploy(map)
            break
        case 'python':
            pythonDeploy(map)
            break
        case 'go':
            goDeploy(map)
            break
        case 'java':
            javaDeploy(map)
            break
    }
}
