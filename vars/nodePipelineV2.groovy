def call(Map config = [:]) {
    // Mapeo de valores ingresados desde el mapa de configuración

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


    pipeline {
        agent {
            docker {
                image nodeImage
            }
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
        }
    }
}