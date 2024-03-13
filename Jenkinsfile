// This pipeline uses Jenkins-Agent label to run the pipeline on a Jenkins agent with that label
// It also defines the JDK and Maven tools to be used during the build
pipeline {
    agent { label 'Jenkins-Agent' }

    // Define the tools to be used during the build
    tools {
        jdk 'JDK-17'
        maven 'maven3.9.6'
    }

    // Define environment variables to be used in the pipeline
    environment {
        SONAR_ORGANIZATION = "nguyenvanhadncntt"
        SONAR_HOST_URL = "https://sonarcloud.io"
        SONAR_PROJECT_KEY = "nguyenvanhadncntt_register-app"
        PROJECT_NAME = "register-app"
    }

    stages {
        // Clean the workspace before starting the build
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        // Checkout the code from GitHub
        stage('Checkout from GitHub') {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/nguyenvanhadncntt/register-app'
            }
        }

        // Run the Maven build
        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        // Run the unit tests
        stage('Test Application') {
            steps {
                sh 'mvn test'
            }
        }

        // Run the SonarQube analysis
        stage("SonarQube Analysis"){
           steps {
	           script {
		            withSonarQubeEnv(credentialsId: 'sonar-key') {  
                        sh "mvn sonar:sonar -Dsonar.organization=${env.SONAR_ORGANIZATION} -Dsonar.projectKey=${env.SONAR_PROJECT_KEY} -Dsonar.host.url=${env.SONAR_HOST_URL} -Dsonar.projectName=${env.PROJECT_NAME}"
		            }
	           }	
            }
        }

        // Wait for the SonarQube Quality Gate
        stage('Quality Gate'){
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-key'
                }
            }
        }
    }
}



