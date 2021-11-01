import jenkins.model.Jenkins
import hudson.security.PermissionGroup
import hudson.security.Permission
import com.michelin.cio.hudson.plugins.rolestrategy.RoleBasedAuthorizationStrategy
import com.michelin.cio.hudson.plugins.rolestrategy.Role
import com.synopsys.arc.jenkins.plugins.rolestrategy.RoleType
import org.jenkinsci.plugins.rolestrategy.permissions.PermissionHelper


Jenkins jenkins = Jenkins.getInstance()
def rbas = new RoleBasedAuthorizationStrategy()
jenkins.instance.setAuthorizationStrategy(rbas)

// --Roles--
def access = [
  admins: ["oleg"],
  builders: [],
  readers: [],
  poweruser: []
]

def globalRoleRead = "read"
def globalBuildRole = "build"
def globalRoleAdmin = "admin"
def folderRoleAdmin = "poweruser"

// --Permisssions--

def adminPermissions = [
"hudson.model.Hudson.Administer",
"hudson.model.Hudson.Read"
]

def readPermissions = [
"hudson.model.Hudson.Read",
"hudson.model.Item.Discover",
"hudson.model.Item.Read"
]

def buildPermissions = [
"hudson.model.Hudson.Read",
"hudson.model.Item.Build",
"hudson.model.Item.Cancel",
"hudson.model.Item.Read",
"hudson.model.Run.Replay"
]

def folderPermissions = [
"hudson.model.Hudson.Administer",
"hudson.model.Hudson.Read"
]

// -- Add Permissions --

Set<Permission> adminPermissionSet = new HashSet<Permission>();
adminPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    adminPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> buildPermissionSet = new HashSet<Permission>();
buildPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    buildPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> readPermissionSet = new HashSet<Permission>();
readPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    readPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

Set<Permission> folderPermissionSet = new HashSet<Permission>();
folderPermissions.each { p ->
  def permission = Permission.fromId(p);
  if (permission != null) {
    folderPermissionSet.add(permission);
  } else {
    println("${p} is not a valid permission ID (ignoring)")
  }
}

// -- Permissions -> Roles --

// admins
Role adminRole = new Role(globalRoleAdmin, adminPermissionSet);
rbas.addRole(RoleType.Global, adminRole);

// builders
Role buildersRole = new Role(globalBuildRole, buildPermissionSet);
rbas.addRole(RoleType.Global, buildersRole);

// read
Role readRole = new Role(globalRoleRead, readPermissionSet);
rbas.addRole(RoleType.Global, readRole);

// poweruser 
Role poweruserRole = new Role(folderRoleAdmin, folderPermissionSet);
rbas.addRole(RoleType.Global, poweruserRole);


// -- Roles -> Groups/Users --

access.admins.each { grantRole ->
  println("Granting admin role to ${grantRole}")
  rbas.assignRole(RoleType.Global, adminRole, grantRole)
}

access.builders.each { grantRole ->
  println("Granting builder role to ${grantRole}")
  rbas.assignRole(RoleType.Global, buildersRole, grantRole)
}

access.readers.each { grantRole ->
  println("Granting read role to ${grantRole}")
  rbas.assignRole(RoleType.Global, readRole, grantRole)
}

access.poweruser.each { grantRole ->
  println("Granting poweruser folder role to ${grantRole}")
  rbas.assignRole(RoleType.Global, poweruserRole, grantRole)
}

jenkins.save()
