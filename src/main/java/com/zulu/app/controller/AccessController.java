package com.zulu.app.controller;

import com.zulu.app.dto.PassphraseRequest;
import com.zulu.app.service.AccessKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<String> validatePassphrase(@RequestBody PassphraseRequest input) {
        log.info("Validating passphrase input");

        boolean valid = service.isValidPassphrase(input.getPassphrase());

        if (valid) {
            return ResponseEntity.ok("Passphrase match");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid passphrase");
        }
    }

    // removes existing password
    @DeleteMapping("/nuke")
    public ResponseEntity<Void> nukePassphrase() {
        log.info("Boom");

        service.nukePassphrase();
        return ResponseEntity.noContent().build();
    }

    // admin only
    @GetMapping("/read")
    public ResponseEntity<PassphraseRequest> readKey() {
        log.info("Reading decrypted passphrase");
        PassphraseRequest response = service.getExistingPassphrase();
        return (response != null) ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }


    @PostMapping("/init")
    public ResponseEntity<String> createKey(@RequestBody PassphraseRequest request) {
        log.info("Storing new passphrase");

        service.storeNewPassphrase(
                request.getPassphrase(),
                request.getDescription()
        );

        return ResponseEntity.ok("Passphrase updated");
    }

}

