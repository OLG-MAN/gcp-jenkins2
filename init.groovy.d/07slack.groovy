import jenkins.plugins.slack.SlackNotifier.*

println "--setting Slack--"

def slack = Jenkins.instance.getExtensionList('jenkins.plugins.slack.SlackNotifier\$DescriptorImpl')[0]
slack.tokenCredentialId = 'PROJECT-ID'
slack.teamDomain = 'PROJECT-TEAM'
slack.room = '#CHAT-ROOM'
slack.save()

println 'Slack global settings configured.'