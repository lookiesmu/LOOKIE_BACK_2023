package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@DiscriminatorValue("B")    //싱글 테이블일 때 구분방법
public class Book extends Item{
    private String author;
    private String isbn;
}
