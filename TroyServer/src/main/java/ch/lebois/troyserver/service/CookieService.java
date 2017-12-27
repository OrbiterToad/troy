package ch.lebois.troyserver.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 * @author Felix
 * @date 05.12.2017
 * <p>
 * Project: login-app
 * Package: ch.felix.loginapp.service
 **/

@Service
public class CookieService {

    //TODO Beispiel Singel Responsibility

    /**
     * Sets current User in Cookie "user"
     */
    public void setUserCookie(HttpServletResponse response, String user) {
        Cookie userCookie = new Cookie("user", user);
        userCookie.setMaxAge(3600);
        userCookie.setPath("/");
        response.addCookie(userCookie);
    }

    /**
     * Returns Current User
     * <p>
     * Wenn cookie user heisst und der Wert nicht null ist gebe den username zurück
     *
     */
    public String getCurrentUser(HttpServletRequest request) throws NullPointerException {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                if (cookie.getValue().equals("")) {
                    throw new NullPointerException();
                }
                return cookie.getValue();
            }
        }
        throw new NullPointerException();
    }

    /**
     * Logout User
     *
     * Set Cookie "user" value to "" and expiry to 0
     */
    public void logout(HttpServletRequest request) throws NullPointerException {
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("user")) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                System.out.println("cookie " + cookie.getMaxAge() + " value = " + cookie.getValue());
            }
        }
    }
}
