package ch.lebois.troyserver.service;

import java.security.MessageDigest;
import org.springframework.stereotype.Service;

/**
 * Project: Hermann
 * Package: ch.lebois.TroyServer.service
 **/

@Service
public class EncodingShaImpl implements EncodingService {

    @Override
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
}
