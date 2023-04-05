package com.example.shop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")//칼럼 이름 설정
    private Long Id;

    @Column(unique = true) // unique 재약 조건을 걸었음
    private String username;

    @Embedded//객체 내장 맵핑을 가능하게하는 에노테이션
    private Address address;

    @OneToMany(mappedBy = "member")//연관관계의 주인은 다수... field 명 명시
    private List<Order> orders = new ArrayList<>();



}
