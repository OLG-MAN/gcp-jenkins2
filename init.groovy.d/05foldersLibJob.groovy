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


// Create folder1, folder1/folder2, folder3
println("### Create folders")
def folder1 = Jenkins.instance.createProject(Folder.class, "folder1")
def folder2 = folder1.createProject(Folder.class, "folder2")
def folder3 = Jenkins.instance.createProject(Folder.class, "folder3")


// Include library to folder1
println("### Create shared library for")
def pipelineLibrarySource = new GitSCMSource("pipeline-library", "https://github.com/OLG-MAN/gcp-jenkins2.git", null, null, null, false)
LibraryConfiguration lc = new LibraryConfiguration("pipeline-library", new SCMSourceRetriever(pipelineLibrarySource))
lc.with {
    implicit = true
    defaultVersion = "master"
}
folder1.addProperty(new FolderLibraries([lc]))
FolderOwnershipHelper.setOwnership(folder, new OwnershipDescription(true, "poweruser"))


// Sample project with a build flow from SCM
println("### Create job for folder3")
WorkflowJob sampleProject = folder3.createProject(WorkflowJob.class, "Job from GH")
GitSCM source = new GitSCM("https://github.com/OLG-MAN/gcp-jenkins2.git")
sampleProject.setDefinition(new CpsScmFlowDefinition(source, "Jenkinsfile"))
JobOwnerHelper.setOwnership(sampleProject, new OwnershipDescription(true, "poweruser"))
