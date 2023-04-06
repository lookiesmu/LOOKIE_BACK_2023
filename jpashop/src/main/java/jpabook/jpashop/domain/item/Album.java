package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("A")    //싱글 테이블일 때 구분방법
public class Album extends Item{
    private String artist;
    private String etc;
}
