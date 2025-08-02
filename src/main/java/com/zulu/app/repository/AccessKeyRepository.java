package com.zulu.app.repository;

import com.zulu.app.entity.AccessKey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessKeyRepository extends MongoRepository<AccessKey, String> {
}
