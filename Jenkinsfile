pipeline {
    agent { label 'Jenkins-Agent' }
    tools {
        jdk 'JDK-17'
        maven 'maven3.9.6'
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
                        sh "mvn sonar:sonar -Dsonar.organization=nguyenvanhadncntt -Dsonar.projectKey=nguyenvanhadncntt_register-app -Dsonar.sources=. -Dsonar.host.url=https://sonarcloud.io"
		            }
	           }	
            }
       }
    }
}
