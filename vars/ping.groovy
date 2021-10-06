#!/usr/bin/env groovy

def call() {
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
}