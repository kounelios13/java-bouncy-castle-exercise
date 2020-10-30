package com.company;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class User {
    private String name;
    private SecretKey key;

    public User(String name, SecretKey key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public byte [] encryptMessage(byte[] message) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        char []b64 = Base64Coder.encode(message);
        Cipher AES_Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        AES_Cipher.init(Cipher.ENCRYPT_MODE,this.key);
        String a = new String(b64);
        return AES_Cipher.doFinal(a.getBytes());
    }

    public void decryptMessage(byte []cipher, String user) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher AES_Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        AES_Cipher.init(Cipher.DECRYPT_MODE,this.key);
        byte [] decrypted = AES_Cipher.doFinal(cipher);
        System.out.println("User "+user+" says:");
        byte [] b64 = Base64.decode(decrypted);
        System.out.println(new String(b64));
    }
}
