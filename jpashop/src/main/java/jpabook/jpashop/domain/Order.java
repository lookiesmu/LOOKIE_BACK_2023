package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //테이블 이름 변경
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //ManyToOne은 디폴트가 EAGER이므로 지연 로딩으로 설정
    @ManyToOne(fetch=FetchType.LAZY)  //멤버 : 주문 = 1 : N,
    @JoinColumn(name = "member_id")
    private Member member;


    //OneToMany는 기본 FetchType이 LAZY임
    //Cascade 설정
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    //OneToOne도 디폴트가 EAGER이므로 지연 로딩으로 설정
    //1:1 연관관계 주인으로 Order, 엑세스를 많이 하는 쪽을 주인으로 두는것이 좋음
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //spring boot hibername는 order_date로 컬럼명이 바뀜
    private LocalDateTime orderDate;    //주문시간, Hibernate가 지원

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 ENUM

    // == 연관관계 메서드 ==
    public void setMamber(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
