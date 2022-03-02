package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.Books;
import com.example.libraryManagement.Models.ElasticBooks;
import com.example.libraryManagement.Repositories.BookElasticcRepo;
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
    public List<ElasticBooks> getElasticBooks(){
        List<ElasticBooks> books=new ArrayList<>();
        bookElastic.findAll().forEach(books::add);
        return books;
    }
    public Books getBookById(String id) {
        return bookRepository.findById(id).get();
    }
    public ElasticBooks getElasticBookById(String id) {
        return bookElastic.findById(id).get();
    }

    public void addBook(Books book){
        bookRepository.save(book);
    }
    public void addBookFast(ElasticBooks book){
        bookElastic.save(book);
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }
    public void deleteById(String id){
        bookRepository.deleteById(id);
    }

    public void updateBook(Books book) {
        bookRepository.save(book);
    }
    public void deleteAllIndex(){
        bookElastic.deleteAll();
    }
    public void deleteIndexById(String id){
        bookElastic.deleteById(id);
    }

    public void updateBookIndex(ElasticBooks book) {
        bookElastic.save(book);
    }
}