package ch.lebois.troyclient;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

public class FileService {

    private String filePath;
    private File file;

    public FileService(String s) {
        setFilePath(s);
    }

    public void setFilePath(String path) {
        this.filePath = path;
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String read() {
        try {
            return new String(java.nio.file.Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String text) {
        String history = read();
        try {
            Files.write(history + "\n" + text, file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            try {
                file.createNewFile();
                write(text);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public boolean isFile() {
        return file.isFile();
    }

    public void clear() {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path file to be pasted into this file
     */
    public void paste(String path) {
        try {
            if (isFile()) {
                Files.copy(new File(this.filePath), new File(path));
            } else {
                FileUtils.copyDirectory(file, new File(path));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}