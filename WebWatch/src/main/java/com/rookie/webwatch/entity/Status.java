package com.rookie.webwatch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private Set<Order> orders;


    public Status(long status_id, long status, String statusName) {
        this.status_id = status_id;
        this.status = status;
        this.statusName = statusName;
        //this.orders = orders;
    }

}
