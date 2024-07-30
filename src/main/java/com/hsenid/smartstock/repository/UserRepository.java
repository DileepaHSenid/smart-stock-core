package com.hsenid.smartstock.repository;

import com.hsenid.smartstock.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository <User,String> {

    Optional<User> findByUsername(String email);
}