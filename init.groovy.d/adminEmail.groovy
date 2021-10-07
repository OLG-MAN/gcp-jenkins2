import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.model.Jenkins
import jenkins.model.JenkinsLocationConfiguration
import jenkins.security.s2m.AdminWhitelistRule
import org.kohsuke.stapler.StaplerProxy
import hudson.tasks.Mailer



println("Setup admin global email address")
JenkinsLocationConfiguration.get().adminAddress = "admin@jenkins.admin.com"
Mailer.descriptor().defaultSuffix = "@jenkins.admin.com"
