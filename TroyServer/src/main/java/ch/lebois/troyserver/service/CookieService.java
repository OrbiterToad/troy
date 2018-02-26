package ch.lebois.troyserver.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @PROJECT Hermann
 */

@Service
public class CookieService {
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
     * <p>
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
