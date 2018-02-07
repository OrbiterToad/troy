<h1>Installation
<p>Programms needed:
<li>MySql Database
<li>Java JDK 8
<li><a href="https://maven.apache.org/download.cgi">Maven
<li>(Easiest with IntelliJ)

Download Server Application: <a href="https://github.com/Wetwer/game-dev/tree/master/TroyServer">TroyServer

goto `TroyServer\src\main\resources\application.properties`

Create new MySql Database

Set MySql Database and Login Cred (user should be root)
<pre>spring.datasource.url=jdbc:mysql://localhost:3306/[databasename]
spring.datasource.username=[username]
spring.datasource.password=[password]
</pre>

Build TroyServer
<pre>mvn clean package</pre>

Navigieren zu `target/TroyServer-{aktuelleVersion}.jar`

diese Jar Datei ausführen um Server zu starten

Im Browser erreichbar unter: `http://localhost:8080`