package com.example.shop.service;

import com.example.shop.domain.Member;
import com.example.shop.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @PersistenceContext
    EntityManager em;

    @Test
    public void enrollMember() throws Exception{

        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long savedId = memberService.join(member);
        //then
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId)); //객체의 비교를 진행함

    }

    @Test
    public void duplicateUserNameTest() throws IllegalStateException{
        Member member = new Member();
        member.setUsername("kim");

        Member member1 = new Member();
        member1.setUsername("kim");

        memberService.join(member);
        Assertions.assertThrows(IllegalStateException.class, ()->{ memberService.join(member1);});//junit 5 exception handle

//        try{
//            memberService.join(member1);
//        }catch (IllegalStateException e){
//            return;
//        }

        //then
//        fail("오류가 발생해야합니다 !!");
    }

}