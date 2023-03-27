package com.example.shop.domain;

import lombok.Getter;

@Getter
public enum DeliveryStatus { //배송 상태
   READY("준비"),
    COMP("배송");

    private String deliveryStatus;

    DeliveryStatus(String deliveryStatus){
        this.deliveryStatus = deliveryStatus;
    }
   @Override
    public String toString() {
        return deliveryStatus;
    }
}
