package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class XorCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer key) {
        if (key == null) throw new IllegalArgumentException("Key required");

        char[] arr = input.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char) (arr[i] ^ key);
        }
        return new String(arr);
    }

    @Override
    public String decrypt(String input, Integer key) {
        return encrypt(input, key);
    }

    @Override
    public String getType() {
        return "xor";
    }

    @Override
    public String getName() {
        return "XOR Cipher";
    }

    @Override
    public String getDescription() {
        return "Applies a bitwise XOR operation between each character and a numeric key.";
    }

    @Override
    public boolean requiresKey() {
        return true;
    }

    @Override
    public String getExample() {
        return "hello ⊕ 5 → encrypted ⊕ 5 → hello";
    }
}
