package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @JsonManagedReference(value = "category-product")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Product> products;

    @Column(name = "category_name")
    private String categoryName;

    public Category() {
    }

//    public Category(long category_id, String categoryName) {
//        this.category_id = category_id;
//        this.categoryName = categoryName;
//    }
    public Category(long category_id, Set<Product> products, String categoryName) {
        this.category_id = category_id;
        this.products = products;
        this.categoryName = categoryName;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
