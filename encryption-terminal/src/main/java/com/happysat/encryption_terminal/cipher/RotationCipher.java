package com.happysat.encryption_terminal.cipher;

import org.springframework.stereotype.Component;

@Component
public class RotationCipher implements CipherStrategy {

    @Override
    public String encrypt(String input, Integer blockSize) {
        if (blockSize == null || blockSize <= 0)
            throw new IllegalArgumentException("Block size required");

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i += blockSize) {
            int end = Math.min(i + blockSize, input.length());
            result.append(new StringBuilder(input.substring(i, end)).reverse());
        }
        return result.toString();
    }

    @Override
    public String decrypt(String input, Integer blockSize) {
        return encrypt(input, blockSize);
    }

    @Override
    public String getType() {
        return "rotation";
    }

    @Override
    public String getName() {
        return "Rotation Cipher";
    }

    @Override
    public String getDescription() {
        return "Reverse characters inside each block.";
    }

    @Override
    public boolean requiresKey() {
        return true;
    }

    @Override
    public String getExample() {
        return "abcdef + blocksize 3 -> cbafed";
    }
}
