import com.cloudbees.hudson.plugins.folder.Folder
import com.synopsys.arc.jenkins.plugins.ownership.OwnershipDescription
import com.synopsys.arc.jenkins.plugins.ownership.jobs.JobOwnerHelper
import hudson.plugins.git.GitSCM
import jenkins.model.Jenkins
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.ownership.model.folders.FolderOwnershipHelper
import org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import org.jenkinsci.plugins.workflow.libs.FolderLibraries
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever
import org.jenkinsci.plugins.workflow.job.WorkflowJob

// Check folder1 exist
println("=== Create folder1")
if (Jenkins.instance.getItem("Production") != null) {
    println("folder1 has been already initialized, skipping the step")
    return
}

// Create folder1
def folder = Jenkins.instance.createProject(Folder.class, "folder1")


// Include library to folder1
def pipelineLibrarySource = new GitSCMSource("pipeline-library", "https://github.com/OLG-MAN/gcp-jenkins2.git", null, null, null, false)
LibraryConfiguration lc = new LibraryConfiguration("pipeline-library", new SCMSourceRetriever(pipelineLibrarySource))
lc.with {
    implicit = true
    defaultVersion = "master"
}
folder.addProperty(new FolderLibraries([lc]))
FolderOwnershipHelper.setOwnership(folder, new OwnershipDescription(true, "poweruser"))


// Sample project with a build flow from SCM
WorkflowJob sampleProject = folder.createProject(WorkflowJob.class, "Remoting")
GitSCM source = new GitSCM("https://github.com/OLG-MAN/gcp-jenkins2.git")
sampleProject.setDefinition(new CpsScmFlowDefinition(source, "Jenkinsfile"))
JobOwnerHelper.setOwnership(sampleProject, new OwnershipDescription(true, "poweruser"))
