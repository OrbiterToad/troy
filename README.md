THis is actualy not a Game.
-> Its actually a remote access tool.

Development on Module Game has been stoped (never been started).

The Module TroyClient will be installed as a Jar File on desired User PC 

The Module TroyServer is the Server to wich the Data gets sent and controlls the Client.
 It supports:
  - Commandline access
  - Screenshots
  - Chat
and some smaller cool things...

To install client edit ch.lebois.troyclient.TroyClient.java :
<pre>
init.configure("[Here TroyServerIp:Port]", "2.8");
</pre>

To start Server add new MySql DB
Edit File application.properties:
<pre>
spring.datasource.url=jdbc:mysql://[here mysql db and port]/[db name]
spring.datasource.username=root
spring.datasource.password=[root pw]
</pre>
Dont forget to add a user to the db:
<pre>Admin	9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08</pre>
Login: Admin
Password: test
