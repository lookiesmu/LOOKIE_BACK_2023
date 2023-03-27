package com.example.shop.domain;

import com.example.shop.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)//따로 공부할것
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;//진량

    @ManyToMany(mappedBy = "items")//카테고리를 여러개 가질 수 있음.
    private List<Category> categories = new ArrayList<>();
}
