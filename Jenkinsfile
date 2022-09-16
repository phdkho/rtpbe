pipeline {
  agent any
  tools {
    maven 'maven-3.5.2'
  }
  stages {
    stage ('Build') {
      steps {
        sh 'mvn install'
      }
    }
//     stage ('Deploy') {
//       steps {
//         script {
//           deploy adapters: [tomcat9(credentialsId: 'tomcat_credential', path: '', url: 'http://dayal-test.letspractice.tk:8081')], contextPath: '/pipeline', onFailure: false, war: 'webapp/target/*.war'
//         }
//       }
//     }
  }
}