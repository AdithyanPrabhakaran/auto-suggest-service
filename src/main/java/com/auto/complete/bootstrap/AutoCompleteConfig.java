package com.auto.complete.bootstrap;

import com.auto.complete.domainApi.SearchRepository;
import com.auto.complete.jpa.SearchDao;
import com.auto.complete.jpa.SearchRepositoryImpl;
import com.auto.complete.service.SearchService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoCompleteConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${input.file.location}")
    private String fileLocation;

    @Bean("searchService")
    public SearchService getSearchService(final SearchRepository searchRepository){
        return new SearchService(searchRepository,fileLocation);
    }

    @Bean
    public SearchRepository getSearchRepository(final SearchDao searchDao){
        return new SearchRepositoryImpl(searchDao,entityManager);
    }
}
