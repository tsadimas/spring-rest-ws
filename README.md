# spring-rest-ws

To deploy to remote tomcat server:

*add a tomcat7-maven-plugin plugin into pom.xml with the url settings of the server
*create a dettimgs.xml file inside the ~/.m2/ directory and save the credentials and the server id
<code>
<?xml version="1.0" encoding="UTF-8"?>
<settings>
  <servers>
    <server>
      <id>Test</id>
      <username>tomcat-user</username>
      <password>tomcat-password</password>
    </server>
  </servers>
</settings>
</code>
*create a run configuration with mavenand define tomcat7:redeploy as goal
*Make sure that on the remote server, on tomcat-users.xml there are defined the following roles:
<code>
   <role rolename="tomcat-user"/>
   <role rolename="tomcat"/>
   <role rolename="admin"/>
   <role rolename="admin-gui"/>
   <role rolename="manager"/>
   <role rolename="manager-gui"/>
   <role rolename="manager-script"/>
   <user username="tomcat-user" password="tomcat-password" roles="tomcat-user, admin, admin-gui, manager, manager-gui"/>
   <user username="tomcat" password="tomcat-password" roles="tomcat, manager-script"/>
</code>
