package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long detail_id;

    @Column(name = "detail_qty")
    private int detailQty;

    @Column(name = "detail_price")
    private float detailPrice;

    @JsonBackReference(value = "orderDetail")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @JsonBackReference(value = "productOdetail")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(long detail_id, int detailQty, float detailPrice, Order order, Product product) {
        this.detail_id = detail_id;
        this.detailQty = detailQty;
        this.detailPrice = detailPrice;
        this.order = order;
        this.product = product;
    }

    public long getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(long detail_id) {
        this.detail_id = detail_id;
    }

    public int getDetailQty() {
        return detailQty;
    }

    public void setDetailQty(int detailQty) {
        this.detailQty = detailQty;
    }

    public float getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(float detailPrice) {
        this.detailPrice = detailPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
