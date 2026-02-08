package com.happysat.encryption_terminal.dto;

public class CipherInfo {

    private String name;
    private String type;
    private String description;
    private boolean requiresKey;
    private String example;

    public CipherInfo(String name, String type, String description, boolean requiresKey, String example) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.requiresKey = requiresKey;
        this.example = example;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequiresKey() {
        return requiresKey;
    }

    public String getExample() {
        return example;
    }
}
