package com.company;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class Main {
    public static void main(String[] args) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, InvalidKeyException {
        Security.addProvider(new BouncyCastleProvider());
        SecretKey key = Main.GenerateKey();
        User bob = new User("Bob",key);
        User alice = new User("Alice",key);
        String message1 = "hello bob";
        String message2 = "hello alice";
        byte [] cipher1 = alice.encryptMessage(message1.getBytes());
        byte [] cipher2 = bob.encryptMessage(message2.getBytes());
        bob.decryptMessage(cipher1,alice.getName());
        alice.decryptMessage(cipher2,bob.getName());
    }

    // Dimiourgia symmetrikou kleidiouA ES256
    static SecretKey GenerateKey() throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        // make a KeyGenerator object for AES
        KeyGenerator AES_keygen = KeyGenerator.getInstance ("AES");
        // init the generator to produce 256-bit keys and use a random seed
        AES_keygen.init (256, new SecureRandom());
        SecretKey AES_Key = AES_keygen.generateKey ();
        return AES_Key;
    }
}

