package com.example.shop;

import com.example.shop.domain.Member;
import com.example.shop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @Transactional
    @Rollback(value = false)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        
        //when
        Long savedId = memberRepository.save(member);
        Member findMemeber = memberRepository.find(savedId);
        
        //then
        Assertions.assertThat(member.getId()).isEqualTo(findMemeber.getId());
        Assertions.assertThat(findMemeber.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMemeber).isEqualTo(member);


    }
    

}