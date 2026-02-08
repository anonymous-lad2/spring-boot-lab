package com.happysat.encryption_terminal.dto;

import java.util.List;

public class EncryptionTraceResponse {

    private String input;
    private List<CipherTraceStep> steps;
    private String finalOutput;

    public EncryptionTraceResponse(String input, List<CipherTraceStep> steps, String finalOutput) {
        this.input = input;
        this.steps = steps;
        this.finalOutput = finalOutput;
    }

    public String getInput() {
        return input;
    }

    public List<CipherTraceStep> getSteps() {
        return steps;
    }

    public String getFinalOutput() {
        return finalOutput;
    }
}
