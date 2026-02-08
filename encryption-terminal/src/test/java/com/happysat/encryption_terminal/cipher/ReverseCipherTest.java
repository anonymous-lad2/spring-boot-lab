package com.happysat.encryption_terminal.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseCipherTest {

    private final ReverseCipher cipher = new ReverseCipher();

    @Test
    void encrypt_shouldReverseString() {
        assertEquals("olleh", cipher.encrypt("hello", null));
    }

    @Test
    void decrypt_shouldRestoreOrigin() {
        assertEquals("hello", cipher.decrypt("olleh", null));
    }
}
