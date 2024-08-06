package com.auto.complete.domainApi;

import java.util.List;

public interface SearchRepository {
    void persistSearchData(List<String> searchData);
    List<String> getSearchResult(String text) throws InterruptedException;
}
