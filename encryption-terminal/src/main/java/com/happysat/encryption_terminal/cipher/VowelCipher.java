package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class VowelCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer key) {
        return input
                .replaceAll("[aA]", "@")
                .replaceAll("[eE]", "#")
                .replaceAll("[iI]", "!")
                .replaceAll("[oO]", "%")
                .replaceAll("[uU]", "&");
    }

    @Override
    public String decrypt(String input, Integer key) {
        return input
                .replace("@", "a")
                .replace("#", "e")
                .replace("!", "i")
                .replace("%", "o")
                .replace("&", "u");
    }

    @Override
    public String getType() {
        return "vowel";
    }

    @Override
    public String getName() {
        return "Vowel Cipher";
    }

    @Override
    public String getDescription() {
        return "Replaces vowels with predefined symbols to obscure pronunciation while keeping structure readable.";
    }

    @Override
    public boolean requiresKey() {
        return false;
    }

    @Override
    public String getExample() {
        return "hello → h#ll%";
    }
}
