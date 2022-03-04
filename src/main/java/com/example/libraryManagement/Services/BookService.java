package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.Books;
import com.example.libraryManagement.ElasticRepositories.BookElasticcRepo;
import com.example.libraryManagement.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookElasticcRepo bookElastic;

    public List<Books> getBooks(){
        List<Books> books=new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
    public List<Books> getElasticBooks(){
        List<Books> books=new ArrayList<>();
        bookElastic.findAll().forEach(books::add);
        return books;
    }
    public Books getBookById(String id) {
        return bookRepository.findById(id).get();
    }
    public Books getElasticBookById(String id) {
        return bookElastic.findById(id).get();
    }

    public void addBook(Books book){
        bookRepository.save(book);
        bookElastic.save(book);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
        bookElastic.deleteAll();
    }
    public void deleteById(String id){
        bookRepository.deleteById(id);
        bookElastic.deleteById(id);
    }

    public void updateBook(Books book) {
        bookRepository.save(book);
        bookElastic.save(book);
    }

}