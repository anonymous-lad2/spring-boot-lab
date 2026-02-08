package com.happysat.encryption_terminal.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarCipherTest {

    private final CaesarCipher cipher = new CaesarCipher();

    @Test
    void encrypt_shouldShifCharacters() {
        assertEquals("jgnnq", cipher.encrypt("hello", 2));
    }

    @Test
    void decrypt_shouldRestoreOriginal() {
        String encrypted = cipher.encrypt("hello", 2);
        assertEquals("hello", cipher.decrypt(encrypted, 2));
    }

    @Test
    void shouldHandleUppercase() {
        assertEquals("Khoor", cipher.encrypt("Hello", 3));
    }
}
