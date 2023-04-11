package com.example.shop.repository;

import com.example.shop.domain.OrderStatus;
import lombok.Getter;

@Getter
public class OrderSearch {
    OrderStatus orderStatus;
    String userName;
}
