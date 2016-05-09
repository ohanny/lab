package fr.icodem.lab.crypto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;

/**
 * The right way : PBKDF2
 */
public class ComputeHash2 {
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 192; // bits


    public static String hashPassword(String password, String salt) throws Exception {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(
                passwordChars,
                saltBytes,
                ITERATIONS,
                KEY_LENGTH
        );
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();
        return String.format("%x", new BigInteger(hashedPassword));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(hashPassword("password", "salt"));
    }
}
