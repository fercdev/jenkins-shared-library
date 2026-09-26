class NodePipelineConfig {
    DockerConfig docker = new DockerConfig()
    InstallConfig install = new InstallConfig()
    TestConfig test = new TestConfig()
    LintConfig lint = new LintConfig()

    void docker(Closure body) {
        body.delegate = docker
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body()
    }

    void install(Closure body) {
        body.delegate = install
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body()
    }

    void lint(Closure body) {
        body.delegate = lint
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body()
    }

    void test(Closure body) {
        body.delegate = test
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body()
    }
}