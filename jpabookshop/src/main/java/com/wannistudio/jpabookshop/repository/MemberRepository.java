package com.wannistudio.jpabookshop.repository;

import com.wannistudio.jpabookshop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {// <타입, PK>

    // select m from Member m where m.name = ? 로 자동매핑
    List<Member> findByName(String name);

}
