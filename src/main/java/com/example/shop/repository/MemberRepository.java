package com.example.shop.repository;

import com.example.shop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; //spring boot starter jpa 에서 주입 받음.

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username = :name",Member.class)
                .setParameter("name",name)
                .getResultList();

    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }
}
