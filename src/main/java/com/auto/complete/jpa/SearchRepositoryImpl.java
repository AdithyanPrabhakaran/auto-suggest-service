package com.auto.complete.jpa;

import com.auto.complete.domainApi.SearchRepository;
import com.auto.complete.entity.SearchEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
public class SearchRepositoryImpl implements SearchRepository {

    private final SearchDao searchDao;

    private  final EntityManager entityManager;

    @Override
    @Transactional
    public void persistSearchData(List<String> searchData) {
        var searchDataList =  searchData.stream().map(text -> {
           var searchEntity =  new SearchEntity();
           searchEntity.setName(text);
           return searchEntity;
        }).toList();
        searchDao.saveAll(searchDataList);
        log.info("data persisted successfully!");
    }

    @Override
    @Transactional
    public List<String> getSearchResult(String text) {

        log.info("searching for the text: {}",text);
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<SearchEntity> result =
                searchSession
                        .search(SearchEntity.class)
                        .where(f -> f.match().fields("name").matching(text).fuzzy())
                        .fetchAll();
        return result.hits().stream().map(SearchEntity::getName).toList();
    }
}
