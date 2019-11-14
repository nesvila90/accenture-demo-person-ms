pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh '''echo ###NO SEA SAPO###
cd person
mvn install -Dmaven.test.skip=true'''
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