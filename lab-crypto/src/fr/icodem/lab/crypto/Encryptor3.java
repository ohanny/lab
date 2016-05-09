package fr.icodem.lab.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Password-based encryption (PBE) with MD5 and DES
 */
public class Encryptor3 {

    public static void main(String[] args) throws Exception {
        String text = "The Earth";

        String encrypted = encrypt(text, "mypassword");
        String decrypted = decrypt(encrypted, "mypassword");

        System.out.printf("Original  : %s%nEncrypted : %s%nDecrypted : %s", text, encrypted, decrypted);

    }

    public static String encrypt(String text, String password) throws Exception{

        // Get cipher for encryption
        Cipher cipher = makeCipher(password, Cipher.ENCRYPT_MODE);

        // Convert text to byte array
        byte[] decodedData = text.getBytes(StandardCharsets.UTF_8);

        // Encrypt data
        byte[] encodedData = cipher.doFinal(decodedData);
        String encryptedText = Base64.getEncoder().encodeToString(encodedData);

        return encryptedText;
    }

    public static String decrypt(String text, String password) throws Exception {

        // Get cipher for decryption
        Cipher cipher = makeCipher(password, Cipher.DECRYPT_MODE);

        // Decrypt data
        byte[] encodedData = Base64.getDecoder().decode(text);
        byte[] decodedData = cipher.doFinal(encodedData);

        // Convert to String
        String decryptedText = new String(decodedData);

        return decryptedText;
    }


    private static Cipher makeCipher(String password, int decryptMode) throws Exception {
        // Arbitrarily selected 8-byte salt sequence
        final byte[] salt = {
            (byte) 0x43, (byte) 0x76, (byte) 0x95, (byte) 0xc7,
            (byte) 0x5b, (byte) 0xd7, (byte) 0x45, (byte) 0x17
        };

        // Create key derived from password
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(keySpec);

        // Create parameters from the salt and an arbitrary number of iterations
        PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, 100);

        // Get cipher instance
        Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");

        // Set the cipher mode to decryption or encryption
        cipher.init(decryptMode, key, pbeParamSpec);

        return cipher;
    }

}
