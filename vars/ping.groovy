#!/usr/bin/env groovy

// Not working
def call(String addr = 'google.com') {
   sh "ping -c 2 ${addr}"
}

