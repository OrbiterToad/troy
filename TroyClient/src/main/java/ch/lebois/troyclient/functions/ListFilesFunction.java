package ch.lebois.troyclient.functions;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Felix
 * @date 21.02.2018
 * <p>
 * Project: Hermann
 * Package: ch.lebois.troyclient.service
 **/
public class ListFilesFunction {

    public static ArrayList<String> ls(String path) {
        System.out.println("args" + path);
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
