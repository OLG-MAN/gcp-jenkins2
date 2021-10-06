#!/usr/bin/env groovy

def call() {
  scmSkip(deleteBuild: true, skipPattern:'.*\\[SKIP_CI\\].*')
}