class InstallConfig {
    String command = 'npm ci'

    void command(String value){
        this.command = value
    }
}