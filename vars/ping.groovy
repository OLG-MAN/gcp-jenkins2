#!/usr/bin/env groovy


def call(String addr = 'google.com') {
      sh "PING_ADDR=${addr}"
      sh "echo ${addr}"
      sh "ping -c 2 ${PING_ADDR}"
}

return this