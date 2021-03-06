#!/usr/bin/env groovy

def call() {
  sshagent(credentials: ['ssh-key-101']) {
    sh '''
        mkdir ./build-${BUILD_NUMBER}
        cd ./build-${BUILD_NUMBER}
        git clone ${GIT_REPO}
        cd  ./gcp-jenkins2/python_app/src
        GIT_BRANCH=$(git rev-parse --abbrev-ref HEAD)
        zip ${WORKSPACE}/${GIT_BRANCH}-artifact-build-${BUILD_NUMBER}.zip *
        git tag build-${BUILD_NUMBER} HEAD
        git push --tags
    '''
  }
}

// def scmSkip() {
//   scmSkip(deleteBuild: true, skipPattern:'.*\\[SKIP_CI\\].*')
// }

def pingAddr() {
  parallel(
      a: {
          sh 'ping -c 2 ${ADDR1}'
      },
      b: {
          sh 'ping -c 2 ${ADDR2}'
      },
      c: {
          sh 'ping -c 2 ${ADDR3}'
      }
  )
}

