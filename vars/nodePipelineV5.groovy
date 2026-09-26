def call(Closure body) {
    
    // Valores por defecto
    def config = [
        appName: 'nodejs-project',
        nodeImage: 'node:24-alpine',
        installCommand: 'npm ci',
        testCommand: 'npm test',
        lintCommand: 'npm run lint',
        runLint: false
    ]
    
    // Configuracion
    body.delegate = config
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body()

    pipeline {
        agent {
            docker {
                image config.nodeImage
            }
        }

        stages {
            stage('Install dependencies') {
                steps {
                    sh config.installCommand
                }
            }
            stage('Running Tests') {
                steps {
                    sh config.testCommand
                }
            }
            stage('Lint') {
                when {
                    expression { return config.runLint }
                }
                steps {
                    sh config.lintCommand
                }
            }
        }
    }
}