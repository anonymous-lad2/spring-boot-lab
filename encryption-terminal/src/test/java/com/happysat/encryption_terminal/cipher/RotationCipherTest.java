package com.happysat.encryption_terminal.cipher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RotationCipherTest {

    private final RotationCipher cipher = new RotationCipher();

    @Test
    void encrypt_shouldReverseBlocks() {
        assertEquals("cbafed", cipher.encrypt("abcdef", 3));
    }

    @Test
    void decrypt_shouldRestoreOriginal() {
        String encrypted = cipher.encrypt("abcdef", 3);
        assertEquals("abcdef", cipher.decrypt(encrypted, 3));
    }

    @Test
    void shouldHandleUnevenBlocks() {
        assertEquals("cbafedg", cipher.encrypt("abcdefg", 3));
    }
}
