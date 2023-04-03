package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Delivery {//배송
    @Id @GeneratedValue
    Long id;

    @Embedded
    Address address;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;//연관관계의 주인이 아니고 order class 의 delivery 필드에 의해서 맵핑이 된다는 것을 명시함

    @Enumerated(EnumType.STRING)
    DeliveryStatus deliveryStatus;

}
