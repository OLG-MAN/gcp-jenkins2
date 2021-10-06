#!/usr/bin/env groovy

def call(String addr = "addr") {
    script {
        sh '''
        ping -c 2 ${addr}
        '''   
    }     
}
