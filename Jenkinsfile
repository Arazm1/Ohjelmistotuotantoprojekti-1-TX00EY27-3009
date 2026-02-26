
pipeline {
  agent any

  tools {
      maven 'Maven 3.9.11'
  }

  environment {
      PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"
      DOCKERHUB_CREDENTIALS_ID = 'docker-hub'
      DOCKERHUB_REPO = 'arazz/ohjelmistotuotantoprojekti-1-tx00ey27-3009'
      DOCKER_IMAGE_TAG = 'latest'
  }

  stages {
          stage ('check') {
              steps{
                  git url: 'https://github.com/Arazm1/ohjelmistotuotantoprojekti-1-tx00ey27-3009.git', branch: 'Week_6HW'
              }

          }

          stage ('build'){
              steps{
                  bat 'mvn clean install'
              }
          }


          stage ('Report'){
              steps {
                  bat 'mvn jacoco:report'
              }
          }



          stage('Publish Coverage Report') {
              steps {
                  jacoco()
              }
          }

          stage('build image') {
              steps {
                  script {
                      docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                  }
              }
          }

          stage('Push Docker Image to Docker Hub') {
              steps {
                  script {
                      docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                          docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                      }
                  }
              }
          }
      }
  }