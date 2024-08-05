package com.auto.complete.jpa;

import com.auto.complete.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchDao extends JpaRepository<SearchEntity, Long> {
}
