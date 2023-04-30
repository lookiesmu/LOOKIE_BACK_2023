package com.example.shop.repository;

import com.example.shop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    OrderStatus orderStatus;
    String userName;
}
