package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    @Autowired
    ItemRepository ir;
    @Test
    @Transactional
    public void updateTest() throws  Exception{
        Item book = new Book();
        book.setName("111");
        System.out.println(book.getName());
        ir.save(book);
        Book book1 = em.find(Book.class, 1);

        //TX
        book1.setName(("asdf"));
        System.out.println(book.getName());
        //변경감지 = dirty checking
        //TX commit 시 data가 바뀜
    }
}
