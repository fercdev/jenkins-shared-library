def call(Closure body) {
    
    // Valores por defecto
    def config = [
        appName: 'nodejs-project',
        nodeImage: 'node:24-alpine',
        
        install: [
            command: 'npm ci'
        ],
        
        lint: [
            enabled: false,
            command: 'npm run lint'
        ],

        test: [
            command: 'npm test'
        ]
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
                    sh config.install.command
                }
            }
            stage('Running Tests') {
                steps {
                    sh config.test.command
                }
            }
            stage('Lint') {
                when {
                    expression { return config.lint.enabled }
                }
                steps {
                    sh config.lint.command
                }
            }
        }
    }
}