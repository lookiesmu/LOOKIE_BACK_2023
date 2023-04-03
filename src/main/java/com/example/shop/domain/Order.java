package com.example.shop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")//예약어 이기 때문에 orders로 변경
public class Order {

    @Id @GeneratedValue
    Long id;

    @JoinColumn(name="member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    Member member;//실제로 FK 가 생성되는 칼럼으로 연관관계의 주인이다.

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @JoinColumn(name = "deliver_id")
    @OneToOne(fetch = FetchType.LAZY)
    Delivery delivery;

    //하이버 네이트에 의해서 맵핑됨...
    LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드 ==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
