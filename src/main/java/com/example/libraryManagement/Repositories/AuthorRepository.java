package com.example.libraryManagement.Repositories;

import com.example.libraryManagement.Models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author,Integer> {
    public List<Author> findByBooksId(String id);

}
