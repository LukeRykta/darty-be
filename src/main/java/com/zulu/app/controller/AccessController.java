package com.zulu.app.controller;

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

    @PostMapping("/validate")
    public boolean validatePassphrase(@RequestBody String passphrase) {
        log.info("Validating passphrase request");

        return service.isValidPassphrase(passphrase.trim());
    }

    @PostMapping("/init")
    public ResponseEntity<String> initialize(@RequestBody String newPassphrase) {
        log.info("Storing new passphrase");

        service.storeNewPassphrase(newPassphrase.trim());
        return ResponseEntity.ok("New passphrase saved");
    }

    @PutMapping("/update") // Update existing passphrase
    public ResponseEntity<String> update(@RequestBody String newPassphrase) {
        log.info("Update new passphrase");
        service.storeNewPassphrase(newPassphrase.trim());
        return ResponseEntity.ok("Passphrase updated");
    }

}

