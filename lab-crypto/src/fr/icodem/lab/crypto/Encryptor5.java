package fr.icodem.lab.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Password-based encryption (PBE) with HmacSHA1 and AES
 * This code needs jce_policy to be installed in ${java_home}\jre\security
 */
public class Encryptor5 {

    public static void main(String[] args) throws Exception {

        // Text to be crypted and password
        String plainText = "Hello";
        String password = "secret";

        // Generate salt
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = saltBytes = random.generateSeed(20);

        // Alternative syntax
        // SecureRandom random = new SecureRandom();
        // byte[] bytes = new byte[20];
        // random.nextBytes(new byte[20]);
        // String salt = new String(bytes);
        // byte[] saltBytes = salt.getBytes("UTF-8");

        // Derive the key
        int pswdIterations = 65536;
        int keySize = 256;

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),  saltBytes, pswdIterations, keySize);

        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        // Encrypt the message
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] iv = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedText = Base64.getEncoder().encodeToString(encryptedTextBytes);

        // Decrypt the message
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));


        byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        String decryptedText = new String(decryptedTextBytes, StandardCharsets.UTF_8);

        System.out.printf("Original  : %s%nEncrypted : %s%nDecrypted : %s", plainText, encryptedText, decryptedText);

    }

}
