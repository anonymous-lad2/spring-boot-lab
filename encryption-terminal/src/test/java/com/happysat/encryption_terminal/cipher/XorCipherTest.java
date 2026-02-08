package com.happysat.encryption_terminal.cipher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XorCipherTest {

    private final XorCipher cipher = new XorCipher();

    @Test
    void encryptAndDecrypt_shouldBeReversible() {
        String encrypted = cipher.encrypt("hello", 5);
        String decrypted = cipher.decrypt(encrypted, 5);
        assertEquals("hello", decrypted);
    }

    @Test
    void encryptionShouldChangeText() {
        assertNotEquals("hello", cipher.encrypt("hello", 5));
    }
}
