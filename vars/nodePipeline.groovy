def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage("Checkout scm") {
               steps {
                checkout scm
               }
            }

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