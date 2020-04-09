pipeline {
  agent any
  parameters {
        string(defaultValue: '1.0', description: 'releaseversion', name: 'ReleaseVersion')
    } 
  stages {
    stage('Application Checkout From Git') {
      steps {
        git(url: 'https://github.com/repo-gsr/employee-management.git')
      }
    }
    stage('Application Build') {
      steps {
        echo "${params}";
        bat 'mvn clean install -Ddockerfile.skip=true -Dreversion=${params.ReleaseVersion} -Dverbose=true -Dmaven.test.skip=true'
      }
    }
    stage('Application Junit Test') {
      steps {
        bat 'mvn test -Dreversion=${params.ReleaseVersion} -Dverbose=true'
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
        bat 'mvn dockerfile:build -Dreversion=${params.ReleaseVersion}'
      }
    }
    stage('Push Docker Image To Docker Repo') {
      steps {     
       bat 'mvn dockerfile:push -Dreversion=${params.ReleaseVersion}'
      }
    }
  }
}
