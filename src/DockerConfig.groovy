class DockerConfig {
    String image = 'node:24-alpine'

    void image(String value){
        this.image = value
    }
}