package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class SwapCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer key) {
        char[] arr = input.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            char temp = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = temp;
        }
        return new String(arr);
    }

    @Override
    public String decrypt(String input, Integer key) {
        return encrypt(input, key);
    }

    @Override
    public String getType() {
        return "swap";
    }

    @Override
    public String getName() {
        return "Swap Cipher";
    }

    @Override
    public String getDescription() {
        return "Swap adjacent characters.";
    }

    @Override
    public boolean requiresKey() {
        return false;
    }

    @Override
    public String getExample() {
        return "abcd -> badc";
    }
}
