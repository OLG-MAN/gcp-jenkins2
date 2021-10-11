import jenkins.model.Jenkins
import jenkins.model.JenkinsLocationConfiguration
import hudson.tasks.Mailer


println "--setting admin email--"

def adminEmail = 'admin.jenkins@admin.jenkins.com'
def jlc = JenkinsLocationConfiguration.get()
jlc.setAdminAddress(adminEmail)
jlc.save()


println "--setting SMTP server--"

def SMTPHost = 'smtp.server.com'
def mailServer = instance.getDescriptor("hudson.tasks.Mailer")
mailServer.setSmtpHost(SMTPHost)
instance.save()
