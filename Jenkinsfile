pipeline {
    environment {
       registry = "9766945760/khatabook-app"
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
        stage('Code Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar-server') {
                   bat 'mvn clean package sonar:sonar'
                }
            }
        }
        stage('OWASP SCAN') {
            steps {
                dependencyCheck additionalArguments: ' --scan ./ ', odcInstallation: 'DP-Check'
                dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
            }
        }
        stage('Build Artifact') {
            steps {
                bat 'mvn clean package'
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
                      kubernetesDeploy (configs: 'khatabook-db-configmap.yaml', enableConfigSubstitution: false, kubeconfigId: 'kubeconfig')
                      kubernetesDeploy (configs: 'khatabook-db-secrets.yaml', enableConfigSubstitution: false, kubeconfigId: 'kubeconfig')
                      kubernetesDeploy (configs: 'khatabook-db-storage.yaml', enableConfigSubstitution: false, kubeconfigId: 'kubeconfig')
                      kubernetesDeploy (configs: 'khatabook-db-deployment.yaml', enableConfigSubstitution: false, kubeconfigId: 'kubeconfig')
                      kubernetesDeploy (configs: 'khatabook-app-deployment.yaml', enableConfigSubstitution: false, kubeconfigId: 'kubeconfig')
                  }
                  bat 'docker logout'
                  bat 'docker rmi 9766945760/khatabook-app:latest'
                  echo 'SUCCESS'
             }
        }
    }
}
