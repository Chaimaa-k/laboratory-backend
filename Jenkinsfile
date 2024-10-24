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
        stage('Check Docker Image') {
                    steps {
                        script {
                            // Vérifie si l'image Docker existe déjà
                            def imageExists = sh(script: "docker images -q ${DOCKER_IMAGE}:${DOCKER_TAG}", returnStdout: true).trim()

                            if (imageExists) {
                                echo "Docker image ${DOCKER_IMAGE}:${DOCKER_TAG} exists. Skipping build."
                            } else {
                                echo "Docker image ${DOCKER_IMAGE}:${DOCKER_TAG} not found. Building..."
                                // Si l'image n'existe pas, la construire
                                docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                            }
                        }
                    }
                }
        stage('Run Docker Container') {
                    steps {
                        script {
                            // Exécuter le conteneur Docker à partir de l'image construite ou existante
                            echo "Running Docker container from image ${DOCKER_IMAGE}:${DOCKER_TAG}"
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
