import jenkins.model.Jenkins

def systemMessage = "JENKINS SYSTEM MESSAGE HERE"

Jenkins jenkins = Jenkins.getInstance()
jenkins.setSystemMessage(systemMessage)
jenkins.save()