package ch.lebois.troyserver.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

/**
 * @USER Felix
 * @DATE 25.02.2018
 * @PROJECT Hermann
 */

@Service
public class ShaService {

    public String encode(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (byte hashA : hash) {
                String hex = Integer.toHexString(0xff & hashA);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static void main(String[] args) {
        System.out.println(new ShaService().encode("test"));
    }
}
