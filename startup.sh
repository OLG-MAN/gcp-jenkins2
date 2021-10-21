#!/bin/bash

sudo su<< HERE
apt update
apt install openjdk-11-jdk -y
echo -e "\n" | ssh-keygen -t rsa -N ""
apt install wget -y
apt install git -y
apt install zip -y
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt update
sudo apt install jenkins -y
HERE


## yum install centos 
# sudo yum install wget -y
# sudo wget -O /etc/yum.repos.d/jenkins.repo \
#     https://pkg.jenkins.io/redhat-stable/jenkins.repo
# sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io.key
# sudo yum update
# sudo yum install epel-release java-11-openjdk-devel -y
# sudo yum install jenkins -y
# sudo systemctl daemon-reload
