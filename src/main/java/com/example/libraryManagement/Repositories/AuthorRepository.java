package com.example.libraryManagement.Repositories;

import com.example.libraryManagement.Models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author,Integer> {
    public List<Author> findByBooksId(String id);
    @Query(value = "{'books.name' : ?0}")
    public List<Author> findByBooksName(String name);

    @Query(fields = "{'books' : 1}")
    public Author findById(int id);

}
