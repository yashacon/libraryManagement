package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.Author;
import com.example.libraryManagement.Models.Books;
import com.example.libraryManagement.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    public void addAuthor(Author author){
        List<Books> booklist=author.getBooks();
        for(Books h:booklist)
            bookService.addBook(h);
        authorRepository.save(author);
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorByBook(String id) {
        return authorRepository.findByBooksId(id);
    }
}
