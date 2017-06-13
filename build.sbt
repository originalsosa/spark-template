lazy val sparktemplate = project
  .copy(id="sparktemplate")
  .in(file("."))
  .enablePlugins(DeploySSH)
 // .configs(Configs.all:_*)
  .settings(Settings.baseSettings:_*)
 // .settings(Settings.osSettings:_*)
 // .settings(Settings.cdhSettings:_*)
  .settings(Assembly.settings:_*)
  .settings(Deploy.settings:_*)

resolvers += "Cloudera's CDH5 Maven repo" at "https://repository.cloudera.com/artifactory/cloudera-repos/"