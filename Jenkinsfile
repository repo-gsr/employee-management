pipeline {
  agent any
  parameters {
        string(defaultValue: '1.0', description: 'releaseversion', name: 'ReleaseVersion')
    } 
  stages {
    stage('Application Checkout From Git') {
      steps {
        git(credentialsId: 'subbareddygangala_gitrepo_cred', url: 'https://github.com/repo-gsr/employee-management.git')
      }
    }
    stage('Application Build') {
      steps {
        bat 'mvn clean install -Ddockerfile.skip=true -Dreversion=${ReleaseVersion} -Dverbose=true -Dmaven.test.skip=true'
      }
    }
    stage('Application Junit Test') {
      steps {
        bat 'mvn test -Dreversion=${ReleaseVersion} -Dverbose=true'
      }
    }
    stage('Application Code Coverage') {
      steps {     
        jacoco buildOverBuild: true, changeBuildStatus: true, execPattern: '**/target/**.exec', inclusionPattern: '**/*.class'
      }
    }
    stage('Sonar Analysis') {
      steps {     
        bat 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=a7d10b41f3ddfb5b9fe32c3d69f9476ee82ae6b6'
      }
    }
    stage('Building Docker Image') {
      steps {     
        bat 'mvn dockerfile:build -Dreversion=${ReleaseVersion}'
      }
    }
    stage('Push Docker Image To Docker Repo') {
      steps {     
       bat 'mvn dockerfile:push -Dreversion=${ReleaseVersion}'
      }
    }
  }
}
