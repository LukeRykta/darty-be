package com.zulu.app.service;

import com.zulu.app.entity.AccessKey;
import com.zulu.app.repository.AccessKeyRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Service
public class AccessKeyService {

    private final AccessKeyRepository repository;

    public AccessKeyService(AccessKeyRepository repository) {
        this.repository = repository;
    }

    public boolean isValidPassphrase(String input) {
        AccessKey key = repository.findAll().stream().findFirst().orElse(null);
        if (key == null) {
            return false;
        }
        return BCrypt.checkpw(input, key.getHashedPassphrase());
    }

    // admin only
    public void storeNewPassphrase(String passphrase) {
        String hash = BCrypt.hashpw(passphrase, BCrypt.gensalt());
        repository.deleteAll(); // only allow one valid key at a time
        repository.save(new AccessKey(hash));
    }
}
