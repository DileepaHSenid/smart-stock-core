package com.hsenid.smartstock.repository;

import com.hsenid.smartstock.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
    boolean existsByName(String name);
}
