pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh '''echo ###NO SEA SAPO###
cd person
mvn clean install -Dmaven.test.skip=true'''
      }
    }
    stage('test') {
      steps {
        sh '''cd persona
mvn test '''
      }
    }
  }
}