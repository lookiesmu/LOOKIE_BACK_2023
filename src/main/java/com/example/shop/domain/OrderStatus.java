package com.example.shop.domain;

public enum OrderStatus {
    CANCEL("주문취소"),
    ORDER("주문");

    private String orderStatus;

    OrderStatus(String orderStatus){
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
