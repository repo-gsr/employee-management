pipeline {
  agent any
  parameters {
        string(defaultValue: '1.0', description: 'releaseversion', name: 'ReleaseVersion')
    }
  environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    artifactId = readMavenPom().getArtifactId()
    version = readMavenPom().getVersion()
    }
    
  stages {
    stage('Application Checkout From Git') {
      steps {
        git(url: 'https://github.com/repo-gsr/employee-management.git', branch: 'master', changelog: true)
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
