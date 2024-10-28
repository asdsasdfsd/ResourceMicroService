pipeline {
    agent any

    tools {
        jdk 'jdk8'  
        maven 'Maven3'  
    }

    environment {
        DOCKERHUB_REPO_RESOURCEMICROSERVICE = 'sh-resourcemicroservice' 
        DOCKERHUB_CREDENTIALS = 'dockerhub-credentials'                // Docker Hub 凭证
        DOCKERHUB_USER = 'tigerwk'                                     // Docker Hub 用户名
        ALIYUN_ACCESS_KEY_ID = credentials('ALIYUN_ACCESS_KEY_ID')    // 阿里云 Access Key Id
        ALIYUN_ACCESS_KEY_SECRET = credentials('ALIYUN_ACCESS_KEY_SECRET') // 阿里云 Access Key Secret
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

        stage('Build Docker Image with Buildx') {
            steps {
                script {
                    sh "docker buildx create --use"

                    sh """
                    docker buildx build \
                        --file Dockerfile \
                        --tag ${DOCKERHUB_USER}/${DOCKERHUB_REPO_RESOURCEMICROSERVICE}:latest \
                        .
                    """
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

