class LintConfig {
    boolean enabled = false
    String command = 'npm run lint'

    void command(String value){
        this.command = value
    }

    void enabled(boolean value){
        this.enabled = value
    }
}