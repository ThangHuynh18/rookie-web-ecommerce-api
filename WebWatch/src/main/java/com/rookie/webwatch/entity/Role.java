package com.rookie.webwatch.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long role_id;

    @Column(name = "role_name")
    private String name;

//    @JsonBackReference(value = "userRole")
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    Set<User> user = new HashSet<>();
//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//@JoinTable(name = "user",
//        joinColumns = { @JoinColumn(name = "role_id") },
//        inverseJoinColumns = {@JoinColumn(name = "uid") })


    public long getRole_id() {
        return role_id;
    }

    public void setRole_id(long role_id) {
        this.role_id = role_id;
    }

    public String getRoleName() {
        return name;
    }

    public void setRoleName(String roleName) {
        this.name = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<User> getUser() {
//        return user;
//    }
//
//    public void setUser(Set<User> user) {
//        this.user = user;
//    }
}
