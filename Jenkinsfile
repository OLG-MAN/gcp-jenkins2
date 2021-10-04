pipeline {
    agent {
        node {
            label 'node1'
        } 
    }
    
    options {
        parallelsAlwaysFailFast()
    }

    environment {
        GIT_REPO = "git@github.com:OLG-MAN/gcp-jenkins2.git"
    }

    stages {
        stage('Test for skip') {
            steps {
                    scmSkip(deleteBuild: true, skipPattern:'.*\\[SKIP_CI\\].*')
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                sh '''
                    mkdir ./build-${BUILD_NUMBER}
                    cd ./build-${BUILD_NUMBER}
                    git clone ${GIT_REPO}
                    cd  ./gcp-jenkins2/python_app/src
                    GIT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
                    zip ${WORKSPACE}/${GIT_BRANCH}-artifact-build-${BUILD_NUMBER}.zip *
                    git tag build-${BUILD_NUMBER} HEAD
                '''
            }
        }

        stage('Deploy') {
            steps {
                echo 'Without Deploy yet...'
            }
        }

        stage('Run parallel ping') {
            steps {
              parallel(
                a: {
                    sh "ping -c 2 1688.com"
                },
                b: {
                    sh "ping -c 2 2ip.me"
                },
                c: {
                    sh "ping -c 2 8.8.8.8"
                }
              )
            }
        }

        stage('OK') {
            steps {
                echo "-------------JOB is done. OK-------------"
            }
        }   
    }
    
    // Slack notification example
    // post {
    //     success {
    //         slackSend channel: '#ops-room',
    //                 color: 'good',
    //                 message: "The pipeline ${currentBuild.fullDisplayName} completed successfully."
    //     }
    // }
}