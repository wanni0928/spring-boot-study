package com.wannistudio.datajpa.repository;

import com.wannistudio.datajpa.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        Member member = new Member("memberA");
        Member savedMember = memberJpaRepository.save(member);
        Member findMember = memberJpaRepository.find(savedMember.getId());
        assertThat(savedMember.getId()).isEqualTo(findMember.getId());
    }

    @Test
    void basicCRUD() {
        Member member1 = new Member("memberA", 10);
        Member member2 = new Member("memberB", 10);

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // 단건 조회 검증
        Member memberA = memberJpaRepository.findById(member1.getId()).get();
        Member memberB = memberJpaRepository.findById(member2.getId()).get();
        assertThat(memberA).isEqualTo(member1);
        assertThat(memberB).isEqualTo(member2);

        memberA.setUsername("wanni");

        // 리스트 조회
        List<Member> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);

        long deletedCount = memberJpaRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    void findByUsernameAndGreaterThen(){
        Member m1 = new Member("a", 10);
        Member m2 = new Member("a", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> a = memberJpaRepository.findByUserNameAndAgeGreaterThen("a", 10);
        assertThat(a.size()).isEqualTo(1);
    }

    @Test
    void findByUsernameWithNamedQuery() {
        Member m1 = new Member("a", 10);
        Member m2 = new Member("a", 20);
        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> a = memberJpaRepository.findBuUsername("a");
        assertThat(a.size()).isEqualTo(2);
    }

    @Test
    void paging() {
        // given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 10));
        memberJpaRepository.save(new Member("member3", 10));
        memberJpaRepository.save(new Member("member4", 10));
        memberJpaRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        // when
        List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
        long totalCount = memberJpaRepository.totalCount(age);

        // then
        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
    }

    @Test
    void bulkUpdate() {
        // given
        memberJpaRepository.save(new Member("member1", 10));
        memberJpaRepository.save(new Member("member2", 19));
        memberJpaRepository.save(new Member("member3", 20));
        memberJpaRepository.save(new Member("member4", 21));
        memberJpaRepository.save(new Member("member5", 40));

        // when
        int bulkAgePlus = memberJpaRepository.bulkAgePlus(20);

        // then
        assertThat(bulkAgePlus).isEqualTo(3);
    }
}