pipeline{
    agent any
    stages{
        stage('check maven version'){
            steps {
            sh 'mvn --version'
            }
        }
    }
    stages{
            stage('check java version'){
                steps {
                sh 'java -version'
                }
            }
        }
    }
    stages{
            stage('check maven version'){
                steps {
                sh 'mvn verify'
                }
            }
        }
    }
}