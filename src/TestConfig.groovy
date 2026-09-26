class TestConfig {
    String command = 'npm test'

    void command(String value){
        this.command = value
    }
}