#!/bin/bash

sudo su<< HERE
apt update
apt install openjdk-11-jdk -y
mkdir -p /home/oleg_m_gcp/jenkins
apt install git -y
apt install zip -y
HERE