package com.happysat.encryption_terminal.controller;

import com.happysat.encryption_terminal.dto.CipherOverviewResponse;
import com.happysat.encryption_terminal.dto.EncryptionRequest;
import com.happysat.encryption_terminal.dto.EncryptionResponse;
import com.happysat.encryption_terminal.dto.EncryptionTraceResponse;
import com.happysat.encryption_terminal.service.EncryptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Encryption Terminal")
public class EncryptionController {

    private final EncryptionService encryptionService;

    public EncryptionController(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Operation(summary = "Encrypt a message")
    @PostMapping("/encrypt")
    public EncryptionResponse encrypt(
            @Valid @RequestBody EncryptionRequest request
    ) {
        String output = encryptionService.encryptChain(
                request.getMessage(),
                request.getSteps()
        );

        return new EncryptionResponse(
                request.getMessage(),
                output,
                "CHAIN"
        );
    }

    @Operation(summary = "Decrypt a message")
    @PostMapping("/decrypt")
    public EncryptionResponse decrypt(
            @Valid @RequestBody EncryptionRequest request
    ) {
        String output = encryptionService.decryptChain(
                request.getMessage(),
                request.getSteps()
        );

        return new EncryptionResponse(
                request.getMessage(),
                output,
                "CHAIN"
        );
    }

    @PostMapping("/encrypt/trace")
    public EncryptionTraceResponse encryptWithTrace(
            @Valid @RequestBody EncryptionRequest request
    ) {
        return encryptionService.encryptChainWithTrace(
                request.getMessage(),
                request.getSteps()
        );
    }

    @PostMapping("/decrypt/trace")
    public EncryptionTraceResponse decryptWithTrace(
            @Valid @RequestBody EncryptionRequest request
    ) {
        return encryptionService.decryptChainWithTrace(
                request.getMessage(),
                request.getSteps()
        );
    }

    @Operation(summary = "Get information about ciphers and encryption")
    @GetMapping("/ciphers/info")
    public CipherOverviewResponse getCipherInfo(){
        return encryptionService.getCipherOverview();
    }
}
