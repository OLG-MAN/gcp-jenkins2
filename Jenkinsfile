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
                script { 
                    buildingapp.scmSkip2()
                }
            }
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
                        script{
                            buildingapp.pingAddr 'google.com'
                        }
                    }
                }
                stage('Ping 2') {
                    script{
                            buildingapp.pingAddr '2ip.me'
                        }
                }
                stage('Ping 3') {
                    script{
                            buildingapp.pingAddr '8.8.8.8'
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
