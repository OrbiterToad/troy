package ch.lebois.troyclient.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Project: Hermann
 **/
public class Console {

    public static ArrayList<String> execute(String command) {

        ArrayList<String> output = new ArrayList<>();
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = command.split(" ");
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            output.add(System.getProperty("user.dir") + " $ " + command);

            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
                output.add(s);
            }

            while ((s = stdError.readLine()) != null) {
                System.err.println(s);
                output.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return output;
    }
}
