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
    private Order order;

    @Enumerated(EnumType.STRING)
    DeliveryStatus deliveryStatus;

}
