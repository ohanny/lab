package fr.icodem.lab.crypto;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;

/**
 * Key is created from text
 */
public class Encryptor2 {
    public static void main(String[] args) throws Exception {
        // Create key from text
        String keyText = "This is the encryption key";
        KeySpec keySpec = new DESKeySpec(keyText.getBytes(StandardCharsets.UTF_8));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(keySpec);
        System.out.println(key);

        // Create and initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Message to encrypt
        byte[] text = "Very secret message".getBytes(StandardCharsets.UTF_8);

        System.out.println("Text [Hex format] : " + toHex(text));
        System.out.println("Text : " + new String(text));

        // Encrypt the message
        byte[] encrypted = cipher.doFinal(text);

        System.out.println("Encryption : " + toHex(encrypted));

        // Initialize the same cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, key);

        // Decrypt the message
        byte[] decrypted = cipher.doFinal(encrypted);

        System.out.println("Decryption : " + new String(decrypted));

    }

    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}
