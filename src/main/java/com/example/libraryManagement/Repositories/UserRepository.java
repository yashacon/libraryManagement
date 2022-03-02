package com.example.libraryManagement.Repositories;

import com.example.libraryManagement.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,Integer> {
    Optional<User> findByUserName(String UserName);
}
