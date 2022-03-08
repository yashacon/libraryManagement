package com.example.libraryManagement.Services;

import com.example.libraryManagement.Models.Author;
import com.example.libraryManagement.Repositories.AuthorRepository;
import com.example.libraryManagement.dto.AddAuthorRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public Author addAuthor(AddAuthorRequestDTO addAuthorRequestDTO){

        Author author=getAuthorByName(addAuthorRequestDTO.getAuthorFirstName(),addAuthorRequestDTO.getAuthorLastName());
        if(Objects.isNull(author)){
            author=new Author(sequenceGeneratorService.generateSequence(Author.SEQUENCE_NAME),
                    addAuthorRequestDTO.getAuthorFirstName(),addAuthorRequestDTO.getAuthorLastName());
            authorRepository.save(author);
        }
        return author;
    }
    public void deleteAll(){
        authorRepository.deleteAll();
    }
    public void deleteByAuthorId(long id){
        authorRepository.deleteByAuthorId(id);
    }
    public Author updateById(AddAuthorRequestDTO addAuthorRequestDTO,long id){
        Author author=authorRepository.findByAuthorId(id);
        author.setFirstName(addAuthorRequestDTO.getAuthorFirstName());
        author.setLastName(addAuthorRequestDTO.getAuthorLastName());
        authorRepository.save(author);
        return author;
    }
    public Author getAuthorByName(String firstName,String lastName){
        return authorRepository.findByFirstNameAndLastName(firstName,lastName);
    }
    public Author getAuthorById(long id){
        return authorRepository.findByAuthorId(id);
    }
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }
}
