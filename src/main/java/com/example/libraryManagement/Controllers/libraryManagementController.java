package com.example.libraryManagement.Controllers;

import com.example.libraryManagement.Models.*;
import com.example.libraryManagement.Repositories.UserRepository;
import com.example.libraryManagement.Services.AuthorService;
import com.example.libraryManagement.Services.BookService;
import com.example.libraryManagement.Services.UserService;
import com.example.libraryManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class libraryManagementController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserService usersservice;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
//
    @PostMapping("authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch(BadCredentialsException e){
            throw new Exception("Incorrect Username and Password",e);
        }
        final UserDetails userDetails= userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("getAll")
    public List<Books> fun(){
        return bookService.getBooks();
    }
    @GetMapping("getfast/")
    public List<ElasticBooks> Fastfun(){
        return bookService.getElasticBooks();
    }

    @GetMapping("book/{id}")
    public Books getBookID(@PathVariable String id){
        return bookService.getBookById(id);
    }
    @GetMapping("fast/{id}")
    public ElasticBooks getFastBookID(@PathVariable String id){
        return bookService.getElasticBookById(id);
    }

    @PostMapping("add")
    public String addBook(@RequestBody Books book){
        bookService.addBook(book);
        return book.toString();
    }
    @PostMapping("addFast")
    public String addBookFast(@RequestBody ElasticBooks book){
        bookService.addBookFast(book);
        return book.toString();
    }
    @PutMapping("update")
    public String updateBook(@RequestBody Books book){
        bookService.updateBook(book);
        return book.toString();
    }

    @DeleteMapping("delete")
    public String deleteAllBooks(){
        bookService.deleteAll();
        return "Deleted Successfully";
    }
    @DeleteMapping("deleteBook/{id}")
    public String deleteBookID(@PathVariable String id){
        bookService.deleteById(id);
        return "Deleted " +id+" Successfully";
    }
    @PutMapping("updateFast")
    public String updateBookIndex(@RequestBody ElasticBooks book){
        bookService.updateBookIndex(book);
        return book.toString();
    }

    @DeleteMapping("fastDelete")
    public String deleteAllBooksIndex(){
        bookService.deleteAllIndex();
        return "Deleted Successfully";
    }
    @DeleteMapping("fastDeleteBook/{id}")
    public String deleteIndexBookID(@PathVariable String id){
        bookService.deleteIndexById(id);
        return "Deleted " +id+" Successfully";
    }

    @PostMapping("createUser")
    public String createUser(@RequestBody User user){
        usersservice.addUser(user);
        return "Registered Successfully!";
    }

    @PostMapping("addAuthor")
    public Author addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return author;
    }
    @GetMapping("getAllAuthor")
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthor();
    }

    @GetMapping("getAuthorByBook/{id}")
    public List<Author> getAuthorByBook(@PathVariable String id){
        return authorService.getAuthorByBook(id);
    }
}
