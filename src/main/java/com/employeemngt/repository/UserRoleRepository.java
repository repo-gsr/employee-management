package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employeemngt.model.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

}
