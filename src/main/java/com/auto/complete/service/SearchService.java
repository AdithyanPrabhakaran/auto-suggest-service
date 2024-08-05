package com.auto.complete.service;

import com.auto.complete.domainApi.SearchRepository;
import com.auto.complete.exceptions.AutoCompleteException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SearchService {

   private final SearchRepository searchRepository;
   private final String file;

  public ArrayList<String> load_data() throws IOException {
    var dataList = new ArrayList<String>();
    log.info("input file :{}",file);
    try(BufferedReader reader = new BufferedReader(new FileReader(file))){
      String line;
      while ((line = reader.readLine()) != null) {
        dataList.add(line);
      }
    }
    searchRepository.persist_search_data(dataList);
    return dataList;
  }

  public List<String> fetch_suggestions(String text)  {
    List<String> searchResult;
    try {
      searchResult = searchRepository.getSearchResult(text);
    } catch (InterruptedException e) {
      throw new AutoCompleteException(e.getMessage(),e);
    }
    log.info("search result: {}", searchResult);
    return searchResult;
  }
}
