#!/usr/bin/env groovy


def call(String addr = 'google.com') {
   sh "PING_ADDR=${addr}; ping -c 2 ${PING_ADDR}"
}

