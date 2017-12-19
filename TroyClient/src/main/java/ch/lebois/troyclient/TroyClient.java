package ch.lebois.troyclient;

public class TroyClient {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(10000);
            new CommandReciver().readCommands();
        }
    }
}
