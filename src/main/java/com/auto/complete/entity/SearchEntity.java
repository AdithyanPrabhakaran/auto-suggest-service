package com.auto.complete.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import java.io.Serializable;

@Indexed
@Entity
@Table(name = "T_SEARCH")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FullTextField()
    private String name;
}
