package com.hsenid.smartstock.repository;

import com.hsenid.smartstock.entity.SubCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubCategoryRepository  extends MongoRepository<SubCategory, String> {
}
