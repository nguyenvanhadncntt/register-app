pipeline {
    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'JDK-17'
        maven 'maven3.9.6'
    }
    environment {
        // Define environment variables
        SONAR_ORGANIZATION = "nguyenvanhadncntt"
        SONAR_HOST_URL = "https://sonarcloud.io"
        SONAR_PROJECT_KEY = "nguyenvanhadncntt_register-app"
        PROJECT_NAME = "register-app"
    }
    stages {
        stage('clean WorkSpace') {
            steps {
                cleanWs()
            }
        }
        stage('Checkout from github') {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/nguyenvanhadncntt/register-app'
            }
        }
        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test Application') {
            steps {
                sh 'mvn test'
            }
        }
        stage("SonarQube Analysis"){
           steps {
	           script {
		            withSonarQubeEnv(credentialsId: 'sonar-key') { 
                        sh "mvn sonar:sonar -Dsonar.organization=${env.SONAR_ORGANIZATION} -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.projectName=${env.PROJECT_NAME}"
		            }
	           }	
            }
       }
    }
}
