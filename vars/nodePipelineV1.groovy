def call() {
    pipeline {
        agent {
            docker {
                image 'node:24-alpine'
            }
        }

        stages {
            stage ('Npm install') {
                steps {
                    sh "npm ci"
                }
            }

            stage("Run tests") {
                steps {
                    sh "npm test"
                }
            }
        }
    }
}