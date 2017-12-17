package ch.lebois.troyclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Sender {

    private String url = "http://localhost:8080/reciver";

    Sender(){

    }

    Sender(String url){
        setUrl(url);
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void send(String user, String type, String value){
        try {
            String specUrl = url +"/" + user +
                    "/?type=" + URLEncoder.encode(type, "UTF-8") +
                    "&value=" + URLEncoder.encode(value, "UTF-8");
            System.out.println(specUrl);

            System.out.println(new WebHandler(url).getContent());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
