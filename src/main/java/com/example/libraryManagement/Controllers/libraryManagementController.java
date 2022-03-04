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

    /*-----------------JWT API---------------*/
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
    /*-*********************************-*/

    /*----------------Book APIs----------------*/
    @GetMapping("getAll")
    public List<Books> fun(){
        return bookService.getBooks();
    }
    @GetMapping("getfast")
    public List<Books> Fastfun(){
        return bookService.getElasticBooks();
    }

    @GetMapping("book/{id}")
    public Books getBookID(@PathVariable String id){
        return bookService.getBookById(id);
    }
    @GetMapping("fast/{id}")
    public Books getFastBookID(@PathVariable String id){
        return bookService.getElasticBookById(id);
    }

    @PostMapping("add")
    public String addBook(@RequestBody Books book){
        bookService.addBook(book);
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
    /*-*********************************-*/

    /*---------User APIs---------------*/
    @PostMapping("createUser")
    public String createUser(@RequestBody User user){
        usersservice.addUser(user);
        return "Registered Successfully!";
    }
    /*-*********************************-*/


    /*----------Author APIs-------------*/
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

    @GetMapping("getBooksByAuthor/{id}")
    public List<Books> getBooksByAuthor(@PathVariable int id){
        return authorService.getBooksByAuthor(id);
    }

    @GetMapping("getAuthorByBookName/{name}")
    public List<Author> getAuthorByBookName(@PathVariable String name){
        return authorService.getAuthorByBookName(name);
    }

    /*-*********************************-*/
}
