import org.jenkinsci.plugins.github.GitHubPlugin
import org.jenkinsci.plugins.github.config.GitHubServerConfig


println "--setting Github--"

GitHubServerConfig server = new GitHubServerConfig('')
server.setName('GitHubAPI')
server.setApiUrl('https://api.github.com')
GitHubPlugin.configuration().getConfigs().add(server)
GitHubPlugin.configuration().save()  // need test save() method
