package ch.lebois.troyclient.service;

import java.io.IOException;
import java.net.URLEncoder;

public class Sender {

    private final String name;
    private String url;


    public Sender(String name) {
        this.name = name;
        url = "http://localhost:8080/reciver/" + name;
    }

    public void send(String type, String value) {
        try {
            String specUrl = url + "/?type=" + URLEncoder.encode(type, "UTF-8")
                             + "&value=" + URLEncoder.encode(value, "UTF-8");
            new WebHandler(specUrl).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
