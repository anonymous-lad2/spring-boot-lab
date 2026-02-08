package com.happysat.encryption_terminal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Request payload for encryption/decryption")
public class EncryptionRequest {

    @NotBlank(message = "Message must not be empty")
    @Schema(example = "hello")
    private String message;

    // 🔥 for chaining
    @NotNull(message = "Cipher chain is required")
    private List<CipherStep> steps;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CipherStep> getSteps() {
        return steps;
    }

    public void setSteps(List<CipherStep> steps) {
        this.steps = steps;
    }

    // ---------- INNER DTO ----------
    public static class CipherStep {

        @NotBlank(message = "Cipher type is required")
        @Schema(example = "caesar")
        private String type;

        @Min(value = 1, message = "Key must be >= 1")
        @Schema(
                description = "Key value (shift/blockSize/xorKey depending on cipher)",
                example = "2"
        )
        private Integer key;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }
    }
}
