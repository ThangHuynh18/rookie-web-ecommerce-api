package com.rookie.webwatch.dto;

import com.rookie.webwatch.entity.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailDTO {
    private long detail_id;
    private int detailQty;
    private float detailPrice;
    private long order_id;
    private long product_id;

    public OrderDetailDTO() {
    }


    public OrderDetailDTO convertToDto(OrderDetail detail) {
        OrderDetailDTO deatailDTO = new OrderDetailDTO();
        deatailDTO.setDetail_id(detail.getDetail_id());
        deatailDTO.setDetailQty(detail.getDetailQty());
        deatailDTO.setDetailPrice(detail.getDetailPrice());
        deatailDTO.setOrder_id(detail.getOrder().getOrder_id());
        deatailDTO.setProduct_id(detail.getProduct().getProduct_id());

        return deatailDTO;
    }

    public OrderDetail convertToEti(OrderDetailDTO deatailDTO) {
        OrderDetail detail = new OrderDetail();

        detail.setDetailQty(deatailDTO.getDetailQty());
        detail.setDetailPrice(deatailDTO.getDetailPrice());

        return detail;
    }


    public List<OrderDetailDTO> toListDto(List<OrderDetail> listEntity) {
        List<OrderDetailDTO> listDto = new ArrayList<>();

        listEntity.forEach(e->{
            listDto.add(this.convertToDto(e));
        });
        return listDto;
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

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }
}
