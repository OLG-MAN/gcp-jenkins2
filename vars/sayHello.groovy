#!/usr/bin/env groovy

def call(String name = 'human') {
    echo "Hello, ${name}."
    sh "ping -c 2 8.8.8.8"
}