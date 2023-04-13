package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id") //PK 이름 지정
    private Long id;
    private String name;

    @Embedded   //내장 타입임을 명시
    private Address address;

    @OneToMany(mappedBy = "member") //Order에서 member에 의해서 매핑된 미러일 뿐임을 명시(연관관계의 주체가 아님)
    private List<Order> orders = new ArrayList<>();




}
