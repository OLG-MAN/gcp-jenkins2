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