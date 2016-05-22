package src.login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by root on 22.5.16.
 */
public final class Decoder {

    public static boolean isValid(String pass, byte [] original){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        byte [] password = md.digest(pass.getBytes());
        return Arrays.equals(password, original);
    }


    public static byte [] hashPassword(String password){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        return md.digest(password.getBytes());
    }
}
