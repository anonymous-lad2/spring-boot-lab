package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class CaesarCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer shift) {
        if (shift == null) throw new IllegalArgumentException("Shift required");

        shift %= 26;
        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch >= 'a' && ch <= 'z')
                chars[i] = (char) ('a' + (ch - 'a' + shift) % 26);
            else if (ch >= 'A' && ch <= 'Z')
                chars[i] = (char) ('A' + (ch - 'A' + shift) % 26);
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String input, Integer shift) {
        if (shift == null) throw new IllegalArgumentException("Shift required");
        return encrypt(input, 26 - shift);
    }

    @Override
    public String getType() {
        return "caesar";
    }

    @Override
    public String getName() {
        return "Caesar Cipher";
    }

    @Override
    public String getDescription() {
        return "Shifts each alphabetic character by a fixed number of positions.";
    }

    @Override
    public boolean requiresKey() {
        return true;
    }

    @Override
    public String getExample() {
        return "hello + shift 2 -> jgnnq";
    }
}
