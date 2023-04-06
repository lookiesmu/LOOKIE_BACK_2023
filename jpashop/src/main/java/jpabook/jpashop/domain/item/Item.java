package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//상속 관계 테이블의 전략을 지정해주어야함
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   //한 테이블에 다 때려박는 Strategy 사용
@DiscriminatorColumn(name = "dtype")    //싱글 테이블 구분방법 지정
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
