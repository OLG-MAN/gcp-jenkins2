FROM google/cloud-sdk:latest

# Install terraform 
RUN apt-get update && apt-get install -y gnupg software-properties-common curl
RUN curl -fsSL https://apt.releases.hashicorp.com/gpg | apt-key add -
RUN apt-add-repository "deb [arch=amd64] https://apt.releases.hashicorp.com $(lsb_release -cs) main"
RUN apt-get update && apt-get install terraform 

# Template for install special version of terraform
# RUN apt install wget -y
# RUN wget https://releases.hashicorp.com/terraform/<VERSION>/terraform_<VERSION>_linux_amd64.zip
# RUN sudo apt-get install zip -y
# RUN unzip terraform*.zip
# RUN sudo mv terraform /usr/local/bin

# Create TF autocomplete
RUN touch ~/.bashrc
RUN terraform -install-autocomplete

