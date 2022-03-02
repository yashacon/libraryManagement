package com.example.libraryManagement.Repositories;


import com.example.libraryManagement.Models.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Books, String> {

//    public Optional<Books> findById(String id);
//    public Books findByName(String name);

}
