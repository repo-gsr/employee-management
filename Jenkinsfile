//currentBuild.displayName = "employee"
pipeline {
  agent any
  parameters {
        string(defaultValue: '1.0', description: 'releaseversion', name: 'ReleaseVersion')
    } 
  stages {
    stage('Application Checkout From Git') {
      steps {
        currentBuild.displayName = "${GIT_COMMIT}"
        git(url: 'https://github.com/repo-gsr/employee-management.git')
      }
    }
    stage('Application Build') {
      steps {
        echo "${params}";
        echo "${params.ReleaseVersion}"
        bat "mvn clean install -Ddockerfile.skip=true -Dreversion=${params.ReleaseVersion} -Dverbose=true -Dmaven.test.skip=true"
      }
    }
    stage('Application Junit Test') {
      steps {
        bat "mvn test -Dreversion=${params.ReleaseVersion} -Dverbose=true"
      }
    }
    stage('Application Code Coverage') {
      steps {     
        jacoco buildOverBuild: true, changeBuildStatus: true, execPattern: '**/target/**.exec', inclusionPattern: '**/*.class'
      }
    }
    stage('Sonar Analysis') {
      steps {     
        withSonarQubeEnv('sonarqube') {
              bat 'mvn sonar:sonar'
           }
        }
    }
    /*stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
        }
     }*/
    stage('Building Docker Image') {
      steps {     
        bat "mvn dockerfile:build -Dreversion=${params.ReleaseVersion}"
      }
    }
    stage('Push Docker Image To Docker Repo') {
      steps {     
       bat "mvn dockerfile:push -Dreversion=${params.ReleaseVersion}"
      }
    }
  }
}
