package com.example.libraryManagement.Repositories;

import com.example.libraryManagement.Models.ElasticBooks;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookElasticcRepo extends ElasticsearchRepository<ElasticBooks, String> {
}
