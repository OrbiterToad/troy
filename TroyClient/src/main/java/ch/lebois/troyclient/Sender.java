package ch.lebois.troyclient;

import java.io.IOException;
import java.net.URLEncoder;

public class Sender {

    private String url = "http://localhost:8080/reciver";

    Sender() {

    }

    public void setUrl(String url) {
        this.url = url;
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
