package com.rookie.webwatch.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_rating")
public class ProductRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rating_id;

    @Column(name = "rating")
    private int ratingNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
