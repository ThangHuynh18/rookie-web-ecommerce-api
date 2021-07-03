package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id;

    @Column(name = "total_qty")
    private int totalQty;

    @Column(name = "total_price")
    private float totalPrice;

    @JsonBackReference(value = "userOrder")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference(value = "statusOrder")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;

    @JsonManagedReference(value = "orderDetail")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(long order_id, int totalQty, float totalPrice, User user, Status status, Set<OrderDetail> orderDetails) {
        this.order_id = order_id;
        this.totalQty = totalQty;
        this.totalPrice = totalPrice;
        this.user = user;
        this.status = status;
        this.orderDetails = orderDetails;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
