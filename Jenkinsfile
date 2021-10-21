@Library('library-101')_

// env.ADDR1  = "8.8.8.8"
// env.ADDR2  = "1688.com"
// env.ADDR3  = "58.com"

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
        SKIP_COMMIT_MSG = 'SKIP_CI'
        GIT_COMMIT_MSG = sh(script: 'git log -1 --pretty=%B ${GIT_COMMIT}', returnStdout: true).trim()
        ADDR1  = "8.8.8.8"
        ADDR3  = "1688.com"
        ADDR3  = "58.com"
    }

    stages {
        stage('Test for skip and Build App') {
            when {
                expression {
                    env.GIT_COMMIT_MSG != env.SKIP_COMMIT_MSG
                }
            }
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
            step {
                buidingapp.pingAddr()
            }

        }

        stage('OK') {
            steps {
                echo "-------------JOB is done. OK-------------"
            }
        }   
    }
    // --- Slack library notification ---
    // post {
    //     always {
    //         slackNotifier(currentBuild.currentResult)
    //         cleanWs()
    //     }
    // }
}



// parallel{
//     stage('Ping 1') {
//         steps {
//             script{
//                 buildingapp.pingAddr '1.1.1.1'
//             }
//         }
//     }
//     stage('Ping 2') {
//         steps {
//             script{
//                 buildingapp.pingAddr '2ip.me'
//             }
//         }
//     }
//     stage('Ping 3') {
//         steps {
//             script{
//                 buildingapp.pingAddr '1688.com'
//             }
//         }
//     }
// }