#!/usr/bin/env groovy

def call(string) {
    ${env.PING_ADDR} = string
        sh '''
        echo ${env.IP_ADDR}
    '''       
}
