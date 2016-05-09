package fr.icodem.lab.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Encryptor4 {
    public static void main(String[] args) throws Exception {

        String plainText = "The text to be crypted";

        // Get a DES private key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        Key key = keyGen.generateKey();

        // Get a DES cipher object and print the provider
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        System.out.println("\n" + cipher.getProvider().getInfo());

        // Encrypt using the key and the plaintext
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        String encryptedText = Base64.getEncoder().encodeToString(encrypted);

        // Decrypt the ciphertext using the same key
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);
        String decryptedText = new String(decrypted, StandardCharsets.UTF_8);

        System.out.printf("Original  : %s%nEncrypted : %s%nDecrypted : %s", plainText, encryptedText, decryptedText);
    }
}
