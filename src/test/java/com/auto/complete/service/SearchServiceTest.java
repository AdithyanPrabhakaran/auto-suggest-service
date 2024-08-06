package com.auto.complete.service;

import com.auto.complete.domainApi.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

  private SearchService searchService;

  @Mock private SearchRepository searchRepository;

  @BeforeEach
  void beforeEach() throws IOException {
    Resource resource = new ClassPathResource("input.txt");
    searchService = new SearchService(searchRepository,resource.getURI().getPath());
  }

  @Test
  @DisplayName("should read data from input file and persist")
  void load_data() throws IOException {
    doNothing().when(searchRepository).persistSearchData(anyList());
    var result = searchService.loadData();
    assertThat(result).hasSizeGreaterThan(1);
    assertThat(result).containsExactly("Jacob","Michael");
  }

  @Test
  @DisplayName("should fetch auto complete list for search text")
  void fetch_suggestions() throws InterruptedException {

    List<String> expectedResult = List.of("john", "jacob", "henry");
    when(searchRepository.getSearchResult("john")).thenReturn(expectedResult);
    var result = searchService.fetchSuggestions("john");
    assertThat(result).containsExactly("john", "jacob", "henry");
  }
}
