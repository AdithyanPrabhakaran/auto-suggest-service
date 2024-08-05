package com.auto.complete.resource;

import com.auto.complete.service.SearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.auto.complete")
public class AutoCompleteResourceConfig {

    @MockBean private SearchService searchService;

  public static void main(String[] args) {
      SpringApplication.run(AutoCompleteResourceConfig.class,args);
  }
}
