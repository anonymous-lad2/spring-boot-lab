package com.happysat.encryption_terminal.cipher;

import jakarta.validation.constraints.Null;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwapCipherTest {

    private final SwapCipher cipher = new SwapCipher();

    @Test
    void encrypt_shouldSwapAdjacentCharacters() {
        assertEquals("badc", cipher.encrypt("abcd", null));
    }

    @Test
    void decrypt_shouldRestoreOriginal() {
        String encrypted = cipher.encrypt("abcdef", null);
        assertEquals("abcdef", cipher.decrypt(encrypted, null));
    }

    @Test
    void shouldHandleOddLength() {
        assertEquals("badcf", cipher.encrypt("abcdf", null));
    }
}
