import sbt._
import java.io.File

class SubmititProject(info: ProjectInfo) extends ParentProject(info){
  
  val javabin_release = "javaBin Smia" at "http://smia.java.no/maven/repo/release"	
  //Necessary for transitive dependencies in ems-client org.restlet. Project won't build without it
  val maven_restlet = "Public online Restlet repository" at "http://maven.restlet.org"

  val ems_version = "1.1"
  val wicket_version = "1.3.5"
  val junit_version = "4.5"

  override def parallelExecution = true

  lazy val common = project("submitit-common", "Common", new DefaultProject(_){
    val slf4j = "org.slf4j" % "slf4j-log4j12" % "1.4.2"
    val log4j = "log4j" % "log4j" % "1.2.14"
  })
  
  lazy val ems = project("submitit-ems-client", "Ems Client", new DefaultProject(_){
    val ems_client = "no.java.ems" % "ems-client" % ems_version
    val junit = "junit" % "junit" % "4.5" % "test"    	
  }, common)
  
  lazy val ui = project("submitit-webapp", "WebApplication", new DefaultWebProject(_){
    system[File]("submitit.properties")() = "src" / "test" / "resources" / "submitit.properties" asFile
    override def mainResources = super.mainResources +++ descendents( mainSourceRoots, "*" ) --- mainSources
	
    val ems_wiki = "no.java.ems" % "ems-wiki" % ems_version
    val wicket = "org.apache.wicket" % "wicket" % wicket_version
    val wicket_extensions = "org.apache.wicket" % "wicket-extensions" % wicket_version
    val servlet = "javax.servlet" % "servlet-api" % "2.5" % "provided"
    val mail = "javax.mail" % "mail" % "1.4.1"
    val jetty_server = "org.mortbay.jetty" % "jetty" % "6.1.14" % "test"
  }, common, ems)
}