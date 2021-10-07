@Library('library-101')_

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
                buildingapp.scmSkip2 'HELLO'
        }

        stage('Build App') {
            steps {
                buildingapp()
            }
        }

        stage('Deploy') {
            steps {
                echo '----Without Deploy yet----'
            }
        }

        stage('Run parallel ping') {
            parallel{
                stage('Ping 1') {
                    steps {
                        sh "ping -c 2 1688.com"
                    }
                }
                stage('Ping 2') {
                    steps {
                        sh "ping -c 2 2ip.me"
                    }
                }
                stage('Ping 3') {
                    steps {
                        sh "ping -c 2 8.8.8.8"
                    }
                }
            }
        }

        stage('OK') {
            steps {
                echo "-------------JOB is done. OK-------------"
            }
        }   
    }
    
    // Slack library notification
    // post {
    //     always {
    //         slackNotifier(currentBuild.currentResult)
    //         cleanWs()
    //     }
    // }


    // Slack notification example
    // post {
    //     success {
    //         slackSend channel: '#ops-room',
    //                 color: 'good',
    //                 message: "The pipeline ${currentBuild.fullDisplayName} completed successfully."
    //     }
    // }
}