pipeline {
    environment {
       registry = "9766945760/k8s-mysql-app"
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
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-secret', url: 'https://github.com/Angad-Raut/k8s-mysql-demo.git']])
                bat 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }
        stage('Docker Build') {
            steps{
                script {
                    dockerImage = docker.build registry
                    echo 'Build Image Completed'
                }
            }
        }
        stage('Docker Push') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                       dockerImage.push('latest')
                       echo 'Push Image Completed'
                    }
                }
            }
        }
        stage('Deploy To K8s') {
             steps {
                  script{
                      kubernetesDeploy (configs: 'mysql-deployment.yaml',kubeconfigId: 'k8sconfig')
                      kubernetesDeploy (configs: 'app-deployment.yaml',kubeconfigId: 'k8sconfig')
                  }
                  echo 'SUCCESS'
             }
        }
    }
    post{
        always {
    	   bat 'docker logout'
        }
    }
}
