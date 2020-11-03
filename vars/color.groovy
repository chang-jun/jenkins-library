#!/usr/bin/env groovy

void green(String message) {
    ansiColor('xterm') {
        println("\033[1;32m ${message} \033[0m")
    }
}

void yellow(String message) {
    ansiColor('xterm') {
        println("\033[1;33m ${message} \033[0m")
    }
}

void  red(String message) {
    ansiColor('xterm') {
        println("\033[1;31m ${message} \033[0m")
    }
}
