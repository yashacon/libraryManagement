package com.example.libraryManagement.Repositories;

import com.example.libraryManagement.Models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author,Long> {
    public Author findByFirstNameAndLastName(String firstName,String lastName);

    public Author findByAuthorId(long id);

    void deleteByAuthorId(long id);

}
