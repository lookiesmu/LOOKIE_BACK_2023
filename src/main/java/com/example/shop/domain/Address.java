package com.example.shop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

@Embeddable
@AllArgsConstructor//변경 불가능함 클래스 생성함
//값 보호를 위해서 protected 생성자 사용함.
public class Address {
    String city;
    String zipcode;
    String street;

    protected  Address(){

    }
}
