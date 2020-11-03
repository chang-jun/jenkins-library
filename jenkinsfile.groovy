#!/usr/bin/env groovy

library 'jenkins-library@main'

Map map = [
    // Git
    git_url: 'git@github.com:chang-jun/jenkins-library.git',
    git_branch: 'main',
    git_app_group: 'chang-jun',
    git_app_name: 'jenkins-library',
    git_app_language: 'go',

    // Registry
    registry_server: 'registry.hub.docker.com',
    registry_group: 'zhaochangjun',
    registry_app: 'test-app',

    // Jenkins library variable
    project_env: 'testing',
]

// Deploy application
deploy(map)
