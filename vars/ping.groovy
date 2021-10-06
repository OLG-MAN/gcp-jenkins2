#!/usr/bin/env groovy

def call(String name = 'human') {
    script {
        sh '''
        echo ${name}
        ''' 
    }     
}
