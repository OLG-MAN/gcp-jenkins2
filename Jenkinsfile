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
        PING_ADDR = ""
    }

    stages {
        stage('Test for skip') {
            steps {
                    scmSkip(deleteBuild: true, skipPattern:'.*\\[SKIP_CI\\].*')
            }
        }

        stage('lib test') {
            steps {
                test()
            }
        }

        stage('Build') {
            steps {
                build()
            }
        }

        stage('Deploy') {
            steps {
                echo 'Without Deploy yet...'
            }
        }

        stage('Run parallel ping') {
            ping()
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