package com.rookie.webwatch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Product> products;

    @Column(name = "category_name")
    @NotNull
    private String categoryName;

    public Category(long category_id, String categoryName) {
        this.category_id = category_id;
//        this.products = products;
        this.categoryName = categoryName;
    }
}
