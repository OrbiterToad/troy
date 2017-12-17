package ch.lebois.troyclient;

public class TroyClient {
    public static void main(String[] args) {
        new Sender().send("felix", "keylogger", "test");
    }
}
