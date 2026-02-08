package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class ReverseCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer key) {
        return new StringBuilder(input).reverse().toString();
    }

    @Override
    public String decrypt(String input, Integer key) {
        return new StringBuilder(input).reverse().toString();
    }

    @Override
    public String getType() {
        return "reverse";
    }

    @Override
    public String getName() {
        return "Reverse Cipher";
    }

    @Override
    public String getDescription() {
        return "Reverse the entire string character by character.";
    }

    @Override
    public boolean requiresKey() {
        return false;
    }

    @Override
    public String getExample() {
        return "hello -> olleh";
    }
}
