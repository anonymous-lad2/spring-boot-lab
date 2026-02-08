package com.happysat.encryption_terminal.cipher;

public interface CipherStrategy {
    String encrypt(String input, Integer key);

    String decrypt(String input, Integer key);

    String getType();
    String getName();
    String getDescription();
    boolean requiresKey();
    String getExample();
}
