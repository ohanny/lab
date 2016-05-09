package fr.icodem.lab.crypto;

import java.security.MessageDigest;

public class ComputeHash1 {

    public static void main(String[] args) throws Exception {
        String password = "Apple";
        String salt = "Pepper";
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] hash = md.digest((password + salt).getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        System.out.println(sb);
    }

    // *** Apache Common Codec ***
    //String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(stringText);


    // *** Guava ***
    //final String hashed = Hashing.sha256()
    //        .hashString("your input", Charsets.UTF_8)
    //        .toString();

}
