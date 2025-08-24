package com.zulu.app.service;

import com.zulu.app.dto.PassphraseRequest;
import com.zulu.app.entity.AccessKey;
import com.zulu.app.repository.AccessKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessKeyService {

    private final AccessKeyRepository repository;

    private final EncryptionService encryptionService;

    public boolean isValidPassphrase(String input) {
        return repository.findAll()
                .stream()
                .findFirst()
                .map(stored -> {
                    String decrypted = encryptionService.decrypt(stored.getPassphrase());
                    return decrypted.equals(input.trim());
                })
                .orElse(false);
    }

    // admin only
    public void storeNewPassphrase(String passphrase, String description) {
        AccessKey key = new AccessKey(passphrase, description);
        key.setPassphrase(encryptionService.encrypt(passphrase));
        key.setDescription(description);
        repository.save(key);
    }

    public void nukePassphrase() {
        repository.deleteAll();
    }

    public PassphraseRequest getExistingPassphrase() {
        return repository.findAll()
                .stream()
                .findFirst()
                .map(key -> new PassphraseRequest(
                        encryptionService.decrypt(key.getPassphrase()),
                        key.getDescription()))
                .orElse(null);
    }

}
