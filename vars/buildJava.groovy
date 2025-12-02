def call(Map config = [:]) {

    def mvnHome = tool name: config.mavenTool ?: 'maven-3.9.11'

    stage('Checkout Code') {
        git branch: config.branch ?: 'main', url: config.repo
    }

    stage('Build') {
        sh "${mvnHome}/bin/mvn clean install -DskipTests"
    }

    stage('Test') {
        sh "${mvnHome}/bin/mvn test"
    }
}

