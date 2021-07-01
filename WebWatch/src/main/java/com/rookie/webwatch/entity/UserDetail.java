package com.rookie.webwatch.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long udetail_id;

    @Column(name = "udetail_phone")
    private long udetailPhone;

    @Column(name = "udetail_address")
    private String udetailAddress;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserDetail() {
    }

    public UserDetail(long udetail_id, long udetailPhone, String udetailAddress, User user) {
        this.udetail_id = udetail_id;
        this.udetailPhone = udetailPhone;
        this.udetailAddress = udetailAddress;
        this.user = user;
    }

    public long getUdetail_id() {
        return udetail_id;
    }

    public void setUdetail_id(long udetail_id) {
        this.udetail_id = udetail_id;
    }

    public long getUdetailPhone() {
        return udetailPhone;
    }

    public void setUdetailPhone(long udetailPhone) {
        this.udetailPhone = udetailPhone;
    }

    public String getUdetailAddress() {
        return udetailAddress;
    }

    public void setUdetailAddress(String udetailAddress) {
        this.udetailAddress = udetailAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
