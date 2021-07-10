package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.UserDetail;

import java.util.ArrayList;
import java.util.List;

public class UserDetailDTO {
    private long udetail_id;
    private long udetailPhone;
    private String udetailAddress;
    private long user_id;

    public UserDetailDTO() {
    }


    public UserDetailDTO convertToDto(UserDetail detail) {
        UserDetailDTO detailDTO = new UserDetailDTO();
        detailDTO.setUdetail_id(detail.getUdetail_id());
        detailDTO.setUdetailPhone(detail.getUdetailPhone());
        detailDTO.setUdetailAddress(detail.getUdetailAddress());
        detailDTO.setUser_id(detail.getUser().getUser_id());

        return detailDTO;
    }

    public UserDetail convertToEti(UserDetailDTO detailDTO) {
        UserDetail detail = new UserDetail();

        detail.setUdetailPhone(detailDTO.getUdetailPhone());
        detail.setUdetailAddress(detailDTO.getUdetailAddress());

        return detail;
    }


    public List<UserDetailDTO> toListDto(List<UserDetail> listEntity) {
        List<UserDetailDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
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

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
