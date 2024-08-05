package com.auto.complete.resource;

import com.auto.complete.service.SearchService;
import org.openapitools.api.AutoCompleteApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AutoCompleteResource implements AutoCompleteApi {

    @Autowired
    private SearchService searchService;

    @Override
    public ResponseEntity<List<String>> getSearchAutoComplete(String searchText) {
        var searchResult = searchService.fetch_suggestions(searchText);
        return ResponseEntity.ok(searchResult);
    }
}
