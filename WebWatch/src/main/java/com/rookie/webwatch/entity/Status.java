package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long status_id;

    @Column(name = "status")
    private long status;

    @Column(name = "status_name")
    private String statusName;

    @JsonManagedReference(value = "statusOrder")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "status")
    private Set<Order> orders;

    public Status() {
    }

    public Status(long status_id, long status, String statusName, Set<Order> orders) {
        this.status_id = status_id;
        this.status = status;
        this.statusName = statusName;
        this.orders = orders;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
