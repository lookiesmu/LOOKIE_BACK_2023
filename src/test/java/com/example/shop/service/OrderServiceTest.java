package com.example.shop.service;

import com.example.shop.domain.Address;
import com.example.shop.domain.Member;
import com.example.shop.domain.Order;
import com.example.shop.domain.OrderStatus;
import com.example.shop.domain.item.Book;
import com.example.shop.domain.item.Item;
import com.example.shop.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContexts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @Test
    public void orderOrder() throws Exception {
        //given
        Member member = createMember();


        Item book = createBook();

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(getOrder.getStatus(), OrderStatus.ORDER, "상태메세지는 주문이여야합니다");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문한 상품 종류 수가 정확해야한다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량 만큼 재고가  줄어야 한다.");
    }

    @Test
    public void overStockTest() throws Exception{
        //given
        Member member = createMember();
        Item book = createBook2("new book!",10000,1);
        //when
        int orderCount = 11;
        Assertions.assertThrows(RuntimeException.class,()-> orderService.order(member.getId(), book.getId(), orderCount));
        //then
    }


    @Test
    public void 주문취소() {
        //Given
        Member member = createMember();
        Item item = createBook2("시골 JPA", 10000, 10); //이름, 가격, 재고
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //When
        orderService.cancelOrder(orderId);
        //Then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 CANCEL 이다.");
        assertEquals(item.getStockQuantity(),10,"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");

    }


    private Item createBook() {
        Item book = new Book();
        book.setName("new book");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Item createBook2(String name, int price, int stock) {
        Item book = new Book();
        book.setName("new book");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }
    private Member createMember() {
        Member member = new Member();
        member.setUsername("member 1");
        member.setAddress(new Address("도시", "강가", "123123"));
        em.persist(member);
        return member;
    }


}