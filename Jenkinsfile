pipeline {
    agent any
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
