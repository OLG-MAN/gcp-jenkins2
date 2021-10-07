#!/usr/bin/env groovy


def call(String addr = 'google.com') {
   script{
      sh '''
   PING_ADDR="8.8.8.8"
   ping -c 2 ${PING_ADDR}"
   '''
   }
}

