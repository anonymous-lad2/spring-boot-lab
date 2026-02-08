package com.happysat.encryption_terminal.cipher;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VowelCipherTest {

    private final VowelCipher cipher = new VowelCipher();

    @Test
    void encrypt_shouldReplaceVowels() {
        assertEquals("h#ll%", cipher.encrypt("hello", null));
    }

    @Test
    void decrypt_shouldRestoreOriginal() {
        String encrypted = cipher.encrypt("hello", null);
        assertEquals("hello", cipher.decrypt(encrypted, null));
    }

    @Test
    void cipherType_shouldBeVowel() {
        assertEquals("vowel", cipher.getType());
    }
}
