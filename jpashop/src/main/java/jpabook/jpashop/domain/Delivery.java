package jpabook.jpashop.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch=FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    // @Enumerated(EnumType.ORDINAL) Ordinal이 디폴트로 지정되는데, 추가되면 다 밀리므로 절대 사용 X!!!
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;  //READY, COMP
}
