import hudson.model.*
import jenkins.model.*
import com.cloudbees.plugins.credentials.*


// FOR TASK ONLY
// For real use, there should be proper secret management.

credentialId="credential_reference_id_usr_pwd"
credentialDescription="Example credential"
credentialUser="username"
credentialPassword="temp_password"

instance = Jenkins.instance
domain = Domain.global()
store = instance.getExtensionList(
  "com.cloudbees.plugins.credentials.SystemCredentialsProvider")[0].getStore()

usernameAndPassword = new UsernamePasswordCredentialsImpl(
  CredentialsScope.GLOBAL,
  credentialId,
  credentialDescription,
  credentialUser,
  credentialPassword
)

store.addCredentials(domain, usernameAndPassword)
