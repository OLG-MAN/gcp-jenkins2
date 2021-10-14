# Task GCP-Jenkins

## Task 1 

## Main goals.
* Create two VM's (Master and node)
* Install Jenkins on Master, connect static slave node
* Create declarative job
* Add parameter environment
* Add Trigger on push and pull-request
* Add `skip build` if commit message is "SKIP_CI"
* Create zip file with suffix $BRANCH_NAME and store it like artifact and build_number
* Create shared library to send slack notification with build status
* Make parallel ping 3 different servers and if ping failed - stop the job
* Move all logic to shared library

--------------------------------------

## Solution

### 0. Prepare working environment. Create working container with cloud-sdk + terraform

```
# Start container
docker run -it --rm -v ${PWD}:/work -w /work ansible-container:v1
```

### 1. Create 2 VM's (Master, node) and pre-install Jenkins. Connect slave Node.

```
# 2 provisioned VM's with pre-istall scripts in ./main.tf file.
# Pre-install startup script in ./startup.sh file.exit
# Pre-install startup for node in ./startupnode.sh file.
# Connect to Slave Node via SSH:
- make ssh-keygen on master
- copy public key to slave node to path ~/.ssh/authorized_keys
- add needed values in node connection UI (picture below)
- add private key to credentials in node connection UI for connection.
```

![Add Node form](img/1.jpg)

### 2. Create declarative job. With:

* Parameter environment
* Making .zip artifact with suffix $BRANCH_NAME and build_number
* Skip build option if have target pattern `SKIP_CI` in commit 
* Parallel ping
* "Build-number" tag to last commit after success build

```
# Jenkins Pipeline in ./Jenkins file
# Update: Now part of logic moved to shared library
```

### 3. Add GitLab/GitHub Webhook

```
### GitLab ###
1. Install Gitlab plugin in jenkins and restart.
2. In `job` configuration activate GitLab Build trigger. (pic 2)
3. Add Jenkins integration in project repo. (pic 3)
4. Test connection and test push event in repo for Webhook trigger. (pic 4)
```

![2](img/2.jpg)

![3](img/3.jpg)

![4](img/4.jpg)

```
### GitHub ###
1. Add webhook in GitHub project setting with jenkins server URL (pic 5,6)
2. Check `GitHub hook trigger for GITScm polling` (pic 7)
```

![5](img/5.jpg)

![6](img/6.jpg)

![7](img/7.jpg)


### 4. Add shared library, move pipeline logic 

* Install needed plugins for Jenkins
* Create ./vars folder with script .groovy files
* Import shared library into Jenkinsfile

```
# Pipeline logic moved to shared library in ./vars/*.groovy files.
```

--------------------------------------

## Task 2 

## Main goals.
## Make Jenkins init script With:
* Setup system message
* setup global admin email address
* setup smtp server
* setup slack
* setup github
* Create three folders `/folder1`, `/folder1/folder2` and `/folder3`
* For `folder1` configure your shared library
* Create credentials `USERNAME` and `PASSWORD`
* Create group and role `poweruser` and assing it to `folder1`
* Inside `folder3` create test-job with build permissions for `poweruser`

## Solution 

### 1. Make init scripts 

```
# Jenkins init scripts in ./init.groovy.d/*.groovy files
```
----------------------------------------

## Task 3 (optional)

### Make Configuratioin as a Code with CasC plugin

* Install and configure CasC plugin

```
# (Not Ready yet)
# Jenkins casc script in ./casc/jenkins.yaml
```

----------------------------------------
