pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }
        stage('Docker Build & Push') {
            steps {
                sh 'docker build -t my-ecr-repo:${GIT_COMMIT} .'
                sh 'docker push my-ecr-repo:${GIT_COMMIT}'
            }
        }
        stage('Deploy to Staging') {
            steps {
                sh 'kubectl apply -f k8s/staging.yaml'
            }
        }
        stage('Smoke Test') {
            steps {
                sh 'curl -f http://staging.myapp/health'
            }
        }
    }
    post {
        success {
            echo "✅ Pipeline succeeded!"
        }
        failure {
            echo "❌ Pipeline failed!"
        }
    }
}
