package ch.lebois.troyclient.service;

import ch.lebois.troyclient.main.SystemVariables;

import java.io.IOException;
import java.net.URLEncoder;

public class Sender {


    private final String url;

    public Sender(String url) {

        this.url = url;
    }


    public void send(String type, String value) {
        try {
            String specUrl = url + "/reciver/" + SystemVariables.CLIENT_NAME + "/?type="
                             + URLEncoder.encode(type, "UTF-8")
                             + "&value=" + URLEncoder.encode(value, "UTF-8");
            new WebHandler(specUrl).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
