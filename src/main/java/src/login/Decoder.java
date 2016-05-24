package src.login;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by root on 22.5.16.
 */
public final class Decoder {

    public static boolean isValid(String pass, char[] original) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        char[] password = new HexBinaryAdapter().marshal(md.digest(pass.getBytes())).toCharArray();
        return Arrays.equals(password, original);
    }


    public static char[] hashPassword(String password) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        return new HexBinaryAdapter().marshal(md.digest(password.getBytes())).toCharArray();
    }
}
