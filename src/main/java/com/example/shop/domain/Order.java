package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")//예약어 이기 때문에 orders로 변경
@Getter
@Setter
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
    LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    //==연관관계 메서드 ==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {//양방향 관계를 한번에 설정할 수 있게 만들어줌
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public static Order creatOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);//맴버설정
        order.setDelivery(delivery);//배달 객체 설정
        for(OrderItem orderItem: orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);//상태설정
        order.setOrderDate(LocalDateTime.now());//날짜 설정.
        return order;
    }

    public void cancel(){
        if(delivery.getDeliveryStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 배송된 상품은 취소가 불가능합니다");
        }

        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:orderItems){
            orderItem.cancel();
        }

    }

    //==조회로직 ==/
    public int getTotalPrice(){
        int orderPrice = 0;
        for(OrderItem orderItem: orderItems){
            orderPrice += orderItem.getTotalPrice();
        }

        return orderPrice;
    }
}
