package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.Author;
import com.example.libraryManagement.Models.Books;
import com.example.libraryManagement.ElasticRepositories.BookElasticcRepo;
import com.example.libraryManagement.Repositories.BookRepository;
import com.example.libraryManagement.dto.AddAuthorRequestDTO;
import com.example.libraryManagement.dto.AddBookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookElasticcRepo bookElastic;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

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
    public Books getBookById(long id) {
        return bookRepository.findByBookId(id);
    }
    public Books getElasticBookById(long id) {
        return bookElastic.findByBookId(id);
    }

    public Books addBook(AddBookRequestDTO addBookRequestDTO){
        AddAuthorRequestDTO addAuthorRequestDTO=new AddAuthorRequestDTO();
        Author author=authorService.getAuthorByName(addBookRequestDTO.getAuthorFirstName(),addBookRequestDTO.getAuthorLastName());
        if(Objects.isNull(author)) {
            addAuthorRequestDTO.setAuthorFirstName(addBookRequestDTO.getAuthorFirstName());
            addAuthorRequestDTO.setAuthorLastName(addBookRequestDTO.getAuthorLastName());

            author = authorService.addAuthor(addAuthorRequestDTO);
        }

        Books book=new Books(sequenceGeneratorService.generateSequence(Books.SEQUENCE_NAME),
                author.getAuthorId(),
                addBookRequestDTO.getName(),
                addBookRequestDTO.getDescription());

        bookRepository.save(book);
        try {
            bookElastic.save(book);
        }
        catch(Exception e){

        }
        return book;
    }

    public void deleteAll(){
        bookRepository.deleteAll();
        bookElastic.deleteAll();
    }
    public void deleteById(long id){
        bookRepository.deleteByBookId(id);
        try{
            bookElastic.deleteByBookId(id);
        }
        catch(Exception e){

        }
    }

    public Books updateBook(AddBookRequestDTO addBookRequestDTO,long bookId) {
        Books book=getBookById(bookId);

        Author author=authorService.getAuthorById(book.getAuthor());
        if(!addBookRequestDTO.getAuthorLastName().equals(author.getLastName()) || !addBookRequestDTO.getAuthorFirstName().equals(author.getFirstName()))
        {
            Author authorPresent=authorService.getAuthorByName(addBookRequestDTO.getAuthorFirstName(),addBookRequestDTO.getAuthorLastName());
            if(Objects.isNull(authorPresent)) {
                AddAuthorRequestDTO addAuthorRequestDTO = new AddAuthorRequestDTO();
                addAuthorRequestDTO.setAuthorFirstName(addBookRequestDTO.getAuthorFirstName());
                addAuthorRequestDTO.setAuthorLastName(addBookRequestDTO.getAuthorLastName());
                Author newAuthor = authorService.addAuthor(addAuthorRequestDTO);
                book.setAuthor(newAuthor.getAuthorId());
            }
            else
                book.setAuthor(authorPresent.getAuthorId());
        }

        book.setDescription(addBookRequestDTO.getDescription());
        book.setName(addBookRequestDTO.getName());

        bookRepository.save(book);
        try {
            bookElastic.save(book);
        }
        catch(Exception e){

        }
        return book;
    }

    public List<Books> getBooksByAuthor(long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

}