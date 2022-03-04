package com.example.libraryManagement.ElasticRepositories;

import com.example.libraryManagement.Models.Books;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookElasticcRepo extends ElasticsearchRepository<Books, String> {
}
