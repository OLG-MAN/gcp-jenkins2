#!/usr/bin/env groovy


def call(String addr = 'google.com') {
      sh "PING_ADDR=8.8.8.8"
      sh "echo ${PING_ADDR}"
      sh "ping -c 2 ${PING_ADDR}"
}

return this