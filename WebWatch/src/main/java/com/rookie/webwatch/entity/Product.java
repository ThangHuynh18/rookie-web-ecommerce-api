package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

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
    private String productDescription;

    @Column(name = "product_qty")
    private long productQty;

    //@JsonManagedReference
    @JsonBackReference(value = "categoryProduct")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @JsonManagedReference(value = "productImage")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    //@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Set<ProductImage> productImages;

    @JsonManagedReference(value = "productOdetail")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<OrderDetail> orderDetails;

    @JsonManagedReference(value = "productRating")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<ProductRating> productRatings;

    public Product() {
    }

    public Product(long product_id, String productName, float productPrice, String productDesciption, long productQty, Category category, Set<ProductImage> productImages, Set<OrderDetail> orderDetails, Set<ProductRating> productRatings) {
        this.product_id = product_id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDesciption;
        this.productQty = productQty;
        this.category = category;
        this.productImages = productImages;
        this.orderDetails = orderDetails;
        this.productRatings = productRatings;
    }

    public Product(String productName, float productPrice, String productDescription, long productQty) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productQty = productQty;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Set<ProductRating> getProductRatings() {
        return productRatings;
    }

    public void setProductRatings(Set<ProductRating> productRatings) {
        this.productRatings = productRatings;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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
