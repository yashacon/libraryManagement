package com.example.libraryManagement.Repositories;


import com.example.libraryManagement.Models.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Books, Long> {

    public Books findByBookId(long id);

    void deleteByBookId(long id);
    public List<Books> findByAuthorId(long id);

}
