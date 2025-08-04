package com.zulu.app.controller;

import com.zulu.app.dto.PassphraseRequest;
import com.zulu.app.service.AccessKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/access")
@CrossOrigin(origins = "*") // Allow from frontend origin
public class AccessController {

    private final AccessKeyService service;

    public AccessController(AccessKeyService service) {
        this.service = service;
    }

    // checks passphrase is valid
    @PostMapping("/validate")
    public boolean validatePassphrase(@RequestBody String passphrase) {
        log.info("Validating passphrase request");

        return service.isValidPassphrase(passphrase.trim());
    }


    @GetMapping("/read")
    public ResponseEntity<PassphraseRequest> readPassphrase() {
        PassphraseRequest key = service.getExistingPassphrase();
        if (key == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(key);
    }


    // creates or updates new passphrase
    @PostMapping("/init")
    public ResponseEntity<String> createPassphrase(@RequestBody PassphraseRequest request) {
        log.info("Updating passphrase");

        service.storeNewPassphrase(
                request.getPassphrase().trim(),
                request.getDescription().trim()
        );

        return ResponseEntity.ok("New passphrase saved");
    }

}

