package com.happysat.encryption_terminal.dto;

public class CipherTraceStep {

    private String type;
    private Integer key;
    private String output;

    public CipherTraceStep(String output, Integer key, String type) {
        this.output = output;
        this.key = key;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public Integer getKey() {
        return key;
    }

    public String getOutput() {
        return output;
    }
}
