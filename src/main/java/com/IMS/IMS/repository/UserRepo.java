package com.IMS.IMS.repository;

import com.IMS.IMS.model.Supplier;
import com.IMS.IMS.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo  extends MongoRepository<User, String>{



}
