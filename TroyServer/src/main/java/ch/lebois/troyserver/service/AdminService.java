package ch.lebois.troyserver.service;

import ch.menthe.io.PropertiesHandler;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 05.12.2017
 * <p>
 * Project: login-app
 * Package: ch.felix.loginapp.service
 **/

@Service
public class AdminService {

    private PropertiesHandler handler = new PropertiesHandler("users.properties");

    public boolean userExits(String user, String password) {
        if (userExits(user)) {
            if (handler.getProperty(user).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean userExits(String user) {

        for (String userKey : handler.getKeys()) {
            if (userKey.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getUsers() {
        ArrayList<String> users = new ArrayList<>();
        for (String key : handler.getKeys()) {
            if (!handler.getProperty(key).equals("")) {
                users.add(key);
            }
        }
        return users;
    }


    public String getPassword(String user) {
        if (!handler.getProperty(user).equals("")) {
            return handler.getProperty(user);
        }
        return null;
    }

    public void delete(String user) {
        handler.setValue(user, "");
    }
}
