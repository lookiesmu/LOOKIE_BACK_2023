package com.example.shop.service;

import com.example.shop.domain.*;
import com.example.shop.domain.item.Book;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.doInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void doInit() {
            Member member = createMember("userA", "seoul", "1", "2");
            em.persist(member);

            Member member1 = createMember("userB", "부산", "2", "3");
            em.persist(member1);

            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA2 BOOK", 20000, 200);
            em.persist(book2);
            OrderItem orderItem = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 20000, 2);
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.creatOrder(member, delivery, orderItem1, orderItem);
            em.persist(order);


        }

        private Member createMember(String userA, String seoul, String zipcode, String street) {
            Member member = new Member();
            member.setUsername(userA);
            member.setAddress(new Address(seoul, zipcode, street));
            return member;

        }

        private Book createBook(String JPA1_BOOK, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(JPA1_BOOK);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }
    }
}
