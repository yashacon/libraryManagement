package com.example.libraryManagement.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Document;


@Document(indexName = "books")
@org.springframework.data.mongodb.core.mapping.Document
public class Books {

    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    @Field(type= FieldType.Keyword)
    private long bookId;
    @Field(type= FieldType.Text)
    private String name;
    @Field(type= FieldType.Text)
    private long authorId;
    @Field(type= FieldType.Text)
    private String description;


    public Books(long bookId,long authorId, String name, String description) {
        this.bookId=bookId;
        this.authorId=authorId;
        this.name = name;
        this.description = description;
    }

    public Books() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(String id) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAuthor() {
        return authorId;
    }

    public void setAuthor(long authorId) {
        this.authorId = authorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + authorId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
