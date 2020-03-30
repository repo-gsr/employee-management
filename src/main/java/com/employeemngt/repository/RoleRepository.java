package com.employeemngt.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.employeemngt.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
