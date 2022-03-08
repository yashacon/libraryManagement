package com.example.libraryManagement.Controllers;

import com.example.libraryManagement.Models.*;
import com.example.libraryManagement.Services.AuthorService;
import com.example.libraryManagement.Services.BookService;
import com.example.libraryManagement.Services.UserService;
import com.example.libraryManagement.dto.AddAuthorRequestDTO;
import com.example.libraryManagement.dto.AddBookRequestDTO;
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
    public Books getBookID(@PathVariable long id){
        return bookService.getBookById(id);
    }
    @GetMapping("fast/{id}")
    public Books getFastBookID(@PathVariable long id){
        return bookService.getElasticBookById(id);
    }

    @PostMapping("add")
    public Books addBook(@RequestBody AddBookRequestDTO addBookRequestDTO){
        return bookService.addBook(addBookRequestDTO);
    }
    @PutMapping("update/{id}")
    public Books updateBook(@RequestBody AddBookRequestDTO addBookRequestDTO,@PathVariable long id){
        return bookService.updateBook(addBookRequestDTO,id);
    }

    @DeleteMapping("delete")
    public String deleteAllBooks(){
        bookService.deleteAll();
        return "Deleted Successfully";
    }
    @DeleteMapping("deleteBook/{id}")
    public String deleteBookID(@PathVariable long id){
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
    public Author addAuthor(@RequestBody AddAuthorRequestDTO addAuthorRequestDTO){
        return authorService.addAuthor(addAuthorRequestDTO);
    }
    @GetMapping("getAllAuthor")
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthor();
    }

    @GetMapping("getAuthor/{id}")
    public Author getAuthorById(@PathVariable long id){
        return authorService.getAuthorById(id);
    }

    @DeleteMapping("deleteAuthors")
    public String deleteAllAuthors(){
        authorService.deleteAll();
        return "Deleted Successfully";
    }
    @DeleteMapping("deleteAuthor/{id}")
    public String deleteAuthorID(@PathVariable long id){
        authorService.deleteByAuthorId(id);
        return "Deleted " +id+" Successfully";
    }
    @PutMapping("updateAuthor/{id}")
    public Author updateAuthor(@RequestBody AddAuthorRequestDTO addAuthorRequestDTO,@PathVariable long id){
        return authorService.updateById(addAuthorRequestDTO,id);
    }



    @GetMapping("getBooksByAuthor/{id}")
    public List<Books> getBooksByAuthor(@PathVariable long id){
        return bookService.getBooksByAuthor(id);
    }

    /*-*********************************-*/
}
