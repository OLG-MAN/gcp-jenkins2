#!/usr/bin/env groovy


def call(String addr = 'google.com') {
      sh "PING_ADDR=${addr}"
      sh "echo ${addr}"
      sh "echo ${PING_ADDR}"
      sh "ping -c 2 google.com"
}

return this