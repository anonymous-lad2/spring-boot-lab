package com.happysat.encryption_terminal.dto;

import java.util.List;

public class CipherOverviewResponse {

    private String whatIsCipher;
    private String whatIsEncryption;
    private String whatIsDecryption;
    private List<CipherInfo> supportedCiphers;

    public CipherOverviewResponse(String whatIsCipher, String whatIsEncryption, String whatIsDecryption, List<CipherInfo> supportedCiphers) {
        this.whatIsCipher = whatIsCipher;
        this.whatIsEncryption = whatIsEncryption;
        this.whatIsDecryption = whatIsDecryption;
        this.supportedCiphers = supportedCiphers;
    }

    public String getWhatIsCipher() {
        return whatIsCipher;
    }

    public String getWhatIsEncryption() {
        return whatIsEncryption;
    }

    public String getWhatIsDecryption() {
        return whatIsDecryption;
    }

    public List<CipherInfo> getSupportedCiphers() {
        return supportedCiphers;
    }
}
