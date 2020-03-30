package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employeemngt.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
