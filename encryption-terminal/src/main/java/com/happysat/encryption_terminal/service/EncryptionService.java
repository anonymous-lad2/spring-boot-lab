package com.happysat.encryption_terminal.service;

import com.happysat.encryption_terminal.cipher.CipherStrategy;
import com.happysat.encryption_terminal.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EncryptionService {

    private final Map<String, CipherStrategy> cipherMap;

    public EncryptionService(List<CipherStrategy> strategies) {
        this.cipherMap = strategies.stream()
                .collect(Collectors.toMap(
                        CipherStrategy::getType,
                        s -> s
                ));
    }

    public String encryptChain(String message, List<EncryptionRequest.CipherStep> steps) {
        String result = message;

        for (EncryptionRequest.CipherStep step : steps) {
            CipherStrategy cipher = getCipher(step.getType());
            result = cipher.encrypt(result, step.getKey());
        }

        return result;
    }

    public String decryptChain(String message, List<EncryptionRequest.CipherStep> steps) {
        String result = message;

        List<EncryptionRequest.CipherStep> reversed = new ArrayList<>(steps);
        Collections.reverse(reversed);

        for (EncryptionRequest.CipherStep step : reversed) {
            CipherStrategy cipher = getCipher(step.getType());
            result = cipher.decrypt(result, step.getKey());
        }

        return result;
    }

    public EncryptionTraceResponse encryptChainWithTrace(
            String message,
            List<EncryptionRequest.CipherStep> steps
    ) {
        String current = message;
        List<CipherTraceStep> trace = new ArrayList<>();

        for (EncryptionRequest.CipherStep step : steps) {
            CipherStrategy cipher = getCipher(step.getType());
            current = cipher.encrypt(current, step.getKey());

            trace.add(new CipherTraceStep(
                    step.getType(),
                    step.getKey(),
                    current
            ));
        }

        return new EncryptionTraceResponse(message, trace, current);
    }

    public EncryptionTraceResponse decryptChainWithTrace(
            String message,
            List<EncryptionRequest.CipherStep> steps
    ) {
        String current = message;
        List<CipherTraceStep> trace = new ArrayList<>();

        List<EncryptionRequest.CipherStep> reversed = new ArrayList<>(steps);
        Collections.reverse(reversed);

        for (EncryptionRequest.CipherStep step : reversed) {
            CipherStrategy cipher = getCipher(step.getType());
            current = cipher.decrypt(current, step.getKey());

            trace.add(new CipherTraceStep(
                    step.getType(),
                    step.getKey(),
                    current
            ));
        }

        return new EncryptionTraceResponse(message, trace, current);
    }

    public CipherOverviewResponse getCipherOverview() {
        List<CipherInfo> cipherInfos = cipherMap.values()
                .stream()
                .map(cipher -> new CipherInfo(
                        cipher.getName(),
                        cipher.getType(),
                        cipher.getDescription(),
                        cipher.requiresKey(),
                        cipher.getExample()
                ))
                .toList();

        return new CipherOverviewResponse(
                "A cipher is a technique used to transform readable text into an unreadable form using a defined set of rules.",
                "Encryption is the process of converting plaintext into ciphertext using a cipher.",
                "Decryption is the process of converting ciphertext back into its original plaintext.",
                cipherInfos
        );
    }

    private CipherStrategy getCipher(String type) {
        CipherStrategy cipher = cipherMap.get(type.toLowerCase());
        if (cipher == null) {
            throw new IllegalArgumentException("Unsupported cipher: " + type);
        }
        return cipher;
    }
}

