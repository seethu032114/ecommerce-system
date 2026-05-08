pipeline {
	agent any

	stages {
		stage('Checkout code') {
			steps {
				git branch: 'main',
				url: 'https://github.com/seethu032114/ecommerce-system.git'
			}
		}

		stage('Build with Maven') {
			agent {
				docker {
					image 'maven:3.9.6-eclipse-temurin-17'
				}
			}
			steps {
				// Run Maven build
				sh 'mvn clean package -DskipTests'
			}
		}

		stage('Docker Build and Deploy') {
			agent {
				docker {
					image 'docker:26.1.0-cli'
					args '-v /var/run/docker.sock:/var/run/docker.sock'
				}
			}
			steps {
				sh 'docker compose build'
			}
		}
	}
}
