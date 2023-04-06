package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// 컴포넌트 스캔의 대상이 되는 어노테이션
@Repository
public class MemberRepository {
    @PersistenceContext //어노테이션으로 스프링이 자동 주입함
    private EntityManager em;

    //멤버 저장
    public Long save (Member member){
        em.persist(member);
        return member.getId();  //커멘드와 쿼리를 분리하자. 스타일임
    }

    //멤버 조회
    public Member find(Long id){
        return em.find(Member.class, id);
    }

    //커멘드 시프트 T로 테스트 코드 만들기
}
