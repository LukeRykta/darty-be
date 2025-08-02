package com.zulu.app.controller;

import com.zulu.app.service.AccessKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return service.isValidPassphrase(passphrase.trim());
    }

    @PostMapping("/init")
    public ResponseEntity<String> initialize(@RequestBody String newPassphrase) {
        service.storeNewPassphrase(newPassphrase.trim());
        return ResponseEntity.ok("New passphrase saved");
    }

    @PutMapping("/update") // Update existing passphrase
    public ResponseEntity<String> update(@RequestBody String newPassphrase) {
        service.storeNewPassphrase(newPassphrase.trim());
        return ResponseEntity.ok("Passphrase updated");
    }

    @DeleteMapping("/delete") // Delete all passphrases
    public ResponseEntity<String> delete() {
        service.deleteAllPassphrases();
        return ResponseEntity.ok("All passphrases deleted");
    }
}

