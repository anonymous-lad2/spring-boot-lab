package com.happysat.encryption_terminal.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response for encryption/decryption")
public class EncryptionResponse {

    @Schema(example = "hello")
    private String input;

    @Schema(example = "jgnnq")
    private String output;

    @Schema(example = "caesar")
    private String type;

    public EncryptionResponse(String input, String output, String type) {
        this.input = input;
        this.output = output;
        this.type = type;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getType() {
        return type;
    }
}
