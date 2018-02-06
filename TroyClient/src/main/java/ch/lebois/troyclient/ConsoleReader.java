package ch.lebois.troyclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Felix
 * @date 06.02.2018
 **/
public class ConsoleReader {

    public static String readString(String question) throws IOException {
        System.out.print(question);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public static Integer readInteger(String question) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print(question);
        return Integer.valueOf(reader.readLine());
    }
}
