import hudson.model.*
import jenkins.model.*
import hudson.tools.*
import hudson.tasks.Mailer
import hudson.util.Secret

println "--setting admin email--"

def adminEmail = 'admin.jenkins@admin.jenkins.com'
def jlc = JenkinsLocationConfiguration.get()
jlc.setAdminAddress(adminEmail)
jlc.save()


println "--setting SMTP server--"

// Vars
def SMTPUser = "username"
def SMTPPassword = "SuPeRpassw0rd1"
def SMTPPort = "256"
def SMTPHost = "smtp.server.qwerty8900.com"

// Constants
def instance = Jenkins.getInstance()
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
def extmailServer = instance.getDescriptor("hudson.plugins.emailext.ExtendedEmailPublisher")

// E-mail Server
mailServer.setSmtpAuth(SMTPUser, SMTPPassword)
mailServer.setSmtpHost(SMTPHost)
mailServer.setSmtpPort(SMTPPort)
mailServer.setCharset("UTF-8")

// Extended-Email
extmailServer.smtpAuthUsername = SMTPUser
extmailServer.smtpAuthPassword = Secret.fromString(SMTPPassword)
extmailServer.smtpHost = SMTPHost
extmailServer.smtpPort = SMTPPort
extmailServer.charset="UTF-8"
extmailServer.defaultSubject="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS!"
extmailServer.defaultBody="\$PROJECT_NAME - Build # \$BUILD_NUMBER - \$BUILD_STATUS:\n\nCheck console output at \$BUILD_URL to view the results."

// Save the state
instance.save()
