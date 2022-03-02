//package com.example.libraryManagement.Configuration;
//
//import org.elasticsearch.client.RestHighLevelClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.RestClients;
//import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.example.libraryManagement.Repositories")
//@ComponentScan(basePackages = {"com.example.libraryManagement"})
//public class config extends AbstractElasticsearchConfiguration {
//
//    @Value("${elasticsearch.url}")
//    public String elasticSearchUrl;
//
//    @Bean
//    @Override
//    public RestHighLevelClient elasticsearchClient() {
//        final ClientConfiguration config=ClientConfiguration.builder()
//                .connectedTo(elasticSearchUrl)
//                .build();
//
//        return RestClients.create(config).rest();
//    }
//}
