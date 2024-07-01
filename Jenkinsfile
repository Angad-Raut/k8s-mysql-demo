pipeline {
    environment {
       registry = "9766945760/k8s-mysql-demo"
       registryCredential = 'dockerhub-credentials'
       dockerImage = ''
    }
    agent any
    tools {
        jdk 'Jdk17'
        maven 'maven-3.8.6'
    }
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/Angad-Raut/k8s-mysql-demo.git'
            }
        }
        stage('Code Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Build Artifact') {
            steps {
                bat 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps{
                bat 'docker build -t 9766945760/k8s-mysql-demo1 .'
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'dockerhub_password', usernameVariable: 'dockerhub-username')]) {
                       bat "echo ${dockerhub_password} | docker login --username ${dockerhub-username} --password-stdin https://registry.docker.io"
                       bat 'docker tag k8s-mysql-demo1 9766945760/k8s-mysql-demo:latest'
                       bat 'docker push 9766945760/k8s-mysql-demo:latest'
                    }
                }
            }
        }
        stage('Deploy To K8s') {
             steps {
                  script{
                      kubernetesDeploy (configs: 'mysql-configMap.yaml',kubeconfigId: 'k8sconfig')
                      kubernetesDeploy (configs: 'mysql-secrets.yaml',kubeconfigId: 'k8sconfig')
                      kubernetesDeploy (configs: 'mysql-deployment.yaml',kubeconfigId: 'k8sconfig')
                      kubernetesDeploy (configs: 'app-deployment.yaml',kubeconfigId: 'k8sconfig')
                  }
                  echo 'SUCCESS'
             }
        }
    }
}
