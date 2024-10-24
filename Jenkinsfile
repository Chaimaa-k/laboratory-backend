pipeline {
    agent any
    environment {
            DOCKER_IMAGE = 'my-spring-boot-app'
            DOCKER_TAG = 'latest'
        }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/Chaimaa-k/laboratory-backend.git'
            }
        }

        stage('Build & Test Backend') {
            steps {
                bat 'mvn clean install '
            }
        }
        stage('Build Docker Image') {
            steps {
                // Construire l'image Docker en utilisant le Dockerfile présent dans le projet
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                    }
                }
            }
        stage('Run Docker Container') {
                    steps {
                       script {
                          // Exécutez le conteneur à partir de l'image construite
                           docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").run('-p 8082:8082')
                }
            }
    }

    post {
        success {
            echo 'Le build, les tests et le déploiement ont réussi !'
        }
        failure {
            echo 'Échec du build, des tests ou du déploiement.'
        }
    }
}
