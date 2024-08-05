package com.auto.complete;

import com.auto.complete.service.SearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories(basePackages  = "com.auto.complete.jpa")
public class SearchTextApplication {

  public static void main(String[] args) throws IOException {
    var appContext = SpringApplication.run(SearchTextApplication.class, args);
    SearchService searchService = (SearchService) appContext.getBean("searchService");
    var data = searchService.load_data();
    assert data.size() > 1 : "error in data loading";
  }
}
