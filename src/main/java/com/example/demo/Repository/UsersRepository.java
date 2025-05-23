package com.example.demo.Repository;

import com.example.demo.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    Users findByEmail(String email);
}