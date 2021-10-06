#!/usr/bin/env groovy

def call(String addr = 'google.com') {
    sh "ping -c 2 ${name}"
}