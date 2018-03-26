package ch.lebois.troyserver.service;

import ch.lebois.troyserver.data.entity.User;
import ch.lebois.troyserver.data.repository.UserRepository;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

/**
 * @PROJECT Hermann
 */


@Service
public class CookieService {

    private UserRepository userRepository;

    public CookieService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
                for (User user : userRepository.findAll()) {
                    if (user.getName().equals(cookie.getValue())) {
                        return cookie.getValue();
                    }
                }
            }
        }
        throw new NullPointerException();
    }
}
