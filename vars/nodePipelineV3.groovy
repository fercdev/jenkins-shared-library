def call(Map config = [:]) {
    // Mapeo de valores ingresados desde el mapa de configuración

    def appName = config.get(
        'appName',
        'node-project'
    )

    def nodeImage = config.get(
        'nodeImage',
        'node:24-alpine'
    )

    def installCommand = config.get(
        'installCommand',
        'npm ci'
    )

    def testCommand = config.get(
        'testCommand',
        'npm test'
    )

    def lintCommand = config.get(
        'lintCommand',
        'npm run lint'
    )

    def runLint = config.get(
        'runLint',
        true
    )


    pipeline {
        agent {
            docker {
                image nodeImage
            }
        }

        environment {
            APP_NAME = "${appName}"
        }

        stages {
            stage ('Install Dependencies') {
                steps {
                    sh installCommand
                }
            }

            stage("Run tests") {
                steps {
                    sh testCommand
                }
            }

            stage("Run lint") {
                when {
                    expression { return runLint }
                }
                steps {
                    sh lintCommand
                }
            }
        }

        post {
            success {
                echo "Pipeline succeeded for ${APP_NAME}"
            }
            failure {
                echo "Pipeline failed for ${APP_NAME}"
            }
            always {
                echo "Pipeline finished"
            }
        }
    }
}