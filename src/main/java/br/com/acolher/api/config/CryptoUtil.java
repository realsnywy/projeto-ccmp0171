package br.com.acolher.api.config;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.beans.factory.annotation.Value;

public class CryptoUtil {

    private static final StrongTextEncryptor encryptor;


    static {
        encryptor = new StrongTextEncryptor();
        encryptor.setPassword(System.getenv("JASYPT_PASS"));
    }

    public static String encrypt(String rawText) {
        return encryptor.encrypt(rawText);
    }

    public static String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}
