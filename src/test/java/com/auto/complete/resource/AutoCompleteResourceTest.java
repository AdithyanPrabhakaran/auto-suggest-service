package com.auto.complete.resource;

import com.auto.complete.service.SearchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AutoCompleteResourceConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AutoCompleteResourceTest {

  private static final String API_URL="/api/v1/auto-completes";
  @Autowired private MockMvc mockMvc;
  @Autowired private SearchService searchService;

    @Test
    void getSearchAutoComplete() throws Exception {

      when(searchService.fetchSuggestions("john")).thenReturn(List.of("john","jacob"));

      RequestBuilder request = get(API_URL)
              .queryParam("searchText","john")
              .contentType(APPLICATION_JSON_VALUE);

    mockMvc.perform(request).andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0]").value("john"))
            .andExpect(jsonPath("$[1]").value("jacob"));
    }
}