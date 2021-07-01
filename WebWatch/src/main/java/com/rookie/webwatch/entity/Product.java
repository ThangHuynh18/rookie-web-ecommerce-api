package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private float productPrice;

    @Column(name = "product_description")
    private String productDesciption;

    @Column(name = "product_qty")
    private long productQty;

    //@JsonManagedReference
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product_image")
    private Set<ProductImage> productImages;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product_detail")
    private Set<OrderDetail> orderDetails;

    //thiếu quan hệ với rating

    public Product() {
    }

    public Product(long product_id, String productName, float productPrice, String productDesciption, long productQty, Category category, Set<ProductImage> productImages) {
        this.product_id = product_id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDesciption = productDesciption;
        this.productQty = productQty;
        this.category = category;
        this.productImages = productImages;
    }

    public Set<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesciption() {
        return productDesciption;
    }

    public void setProductDesciption(String productDesciption) {
        this.productDesciption = productDesciption;
    }

    public long getProductQty() {
        return productQty;
    }

    public void setProductQty(long productQty) {
        this.productQty = productQty;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
