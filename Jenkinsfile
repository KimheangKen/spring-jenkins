pipeline {
  agent any
  tools {
    maven 'maven'
  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install'
        sh 'mvn package'
        sh 'docker build -t kimheang68/jenkins-spring-build .'
      }
    }
    stage('Test') {
      steps {
        echo "Testing..."
        sh 'mvn test'
      }
    }
    stage('Build Image') {
        steps {
            withCredentials([usernamePassword(credentialsId: 'docker-hub-cred', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                script {
                    sh 'docker build -t kimheang68/jenkins-spring-build .'
                    sh "echo \$PASS | docker login -u \$USER --password-stdin"
                    sh 'docker push kimheang68/jenkins-spring-build'
                }
            }
        }
    }
    // stage('Deploy') {
    //   steps {
    //     sh 'docker run -d -p 8888:8080 kimheang68/jenkins-spring-build'
    //   }
    // }
  }
}
