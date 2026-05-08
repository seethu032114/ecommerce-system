pipeline {
	agent any

	stages {
		stage('Checkout code') {
			steps {
				git branch: 'main',
				url: 'https://github.com/seethu032114/ecommerce-system.git'
			}
		}

		stage('Build Maven') {
			steps {
				// Run Maven build
				sh 'mvn clean package -DskipTests'
			}
		}

		stage('Build Docker Images') {
			steps {
				script {
					// Build Docker image using the artifact
					sh 'docker compose build'
				}
			}
		}

		stage('Deploy') {
			steps {
				sh 'docker compose up -d'
			}
		}
	}
}
