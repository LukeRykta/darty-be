package com.zulu.app.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "access_keys")
public class AccessKey {

        @Id
        private String id;

        private String description;

        private String passphrase;

        public AccessKey(String passphrase, String description) {
                this.passphrase = passphrase;
                this.description = description;
        }

}