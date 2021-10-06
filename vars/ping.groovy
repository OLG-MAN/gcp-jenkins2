#!/usr/bin/env groovy

def call(String addr = "8.8.8.8") {
    script {
        sh '''
        ping -c 2 ${addr}
        ''' 
    }     
}
