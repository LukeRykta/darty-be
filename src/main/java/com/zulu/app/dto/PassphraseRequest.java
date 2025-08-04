package com.zulu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PassphraseRequest {
    private String passphrase;
    private String description;
}