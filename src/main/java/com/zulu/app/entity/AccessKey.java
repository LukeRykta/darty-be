package com.zulu.app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "access_keys")
public class AccessKey {

        @Id
        private String id;

        private String passphrase;
        private String description;

        public AccessKey(String passphrase, String description) {
                this.passphrase = passphrase;
                this.description = description;
        }

}