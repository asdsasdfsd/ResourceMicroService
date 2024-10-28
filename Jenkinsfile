pipeline {
    agent any

    tools {
        jdk 'jdk8'  
        maven 'Maven3'  
    }

    environment {
        DOCKERHUB_REPO_RESOURCEMICROSERVICE = 'sh-resourcemicroservice' 
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'        // Docker Hub 凭证
        DOCKERHUB_USER = 'tigerwk'                             // Docker Hub 用户名
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/asdsasdfsd/ResourceMicroService', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def resourceMicroServiceImage = docker.build("${DOCKERHUB_USER}/${DOCKERHUB_REPO_RESOURCEMICROSERVICE}:latest")
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://registry-1.docker.io/v2/', "${DOCKERHUB_CREDENTIALS}") {
                        echo 'Logged in to Docker Hub successfully'
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry-1.docker.io/v2/', "${DOCKERHUB_CREDENTIALS}") {
                        def resourceMicroServiceImage = docker.image("${DOCKERHUB_USER}/${DOCKERHUB_REPO_RESOURCEMICROSERVICE}:latest")
                        resourceMicroServiceImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            script {
                
                sh "docker rmi ${DOCKERHUB_USER}/${DOCKERHUB_REPO_RESOURCEMICROSERVICE}:latest || true"
            }
        }
    }
}




