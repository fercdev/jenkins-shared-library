def call(Closure body) {
    def config = [:]
    body.delegate = config
    body.resolveStrategy = Closure.DELEGATE_FIRST

    body()

    echo "AppName: ${config.appName}"
    echo "NodeImage: ${config.nodeImage}"
    echo "InstallCommand: ${config.installCommand}"
    echo "TestCommand: ${config.testCommand}"
    echo "LintCommand: ${config.lintCommand}"
    echo "RunLint: ${config.runLint}"
}