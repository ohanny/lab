package fr.icodem.lab.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * Key is generated
 */
public class Encryptor1 {
    public static void main(String[] args) throws Exception {
        // Generate key
        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        SecretKey key = keygen.generateKey();

        // Create and initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
