package com.wannistudio.datajpa.repository;

import com.wannistudio.datajpa.dto.MemberDto;
import com.wannistudio.datajpa.entity.Member;
import com.wannistudio.datajpa.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;

    @Test
    void testMember() {
        Member member = new Member("userA");
        Member savedMember = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(savedMember.getId());
        Member member1 = byId.get();
        assertThat(savedMember.getUsername()).isEqualTo(member1.getUsername());
    }

    @Test
    void basicCRUD() {
        Member member1 = new Member("memberA", 10);
        Member member2 = new Member("memberB", 10);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        Member memberA = memberRepository.findById(member1.getId()).get();
        Member memberB = memberRepository.findById(member2.getId()).get();
        assertThat(memberA).isEqualTo(member1);
        assertThat(memberB).isEqualTo(member2);

        memberA.setUsername("wanni");

        // 리스트 조회
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    void findByUsernameAndGreaterThen(){
        Member m1 = new Member("a", 10);
        Member m2 = new Member("a", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> a = memberRepository.findByUsernameAndAgeGreaterThan("a", 10);
        assertThat(a.size()).isEqualTo(1);
    }

    @Test
    void findByUsernameWithNamedQuery() {
        Member m1 = new Member("a", 10);
        Member m2 = new Member("a", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> a = memberRepository.findByUsername("a");
        assertThat(a.size()).isEqualTo(2);
    }

    @Test
    void findUserWithNameAndAge() {
        Member m1 = new Member("a", 10);
        Member member = memberRepository.save(m1);

        List<Member> result = memberRepository.findUserWithNameAndAge("a", 10);

        assertThat(member).isEqualTo(result.get(0));
    }

    @Test
    void findUserNameList() {
        Member m1 = new Member("a", 10);
        Member member = memberRepository.save(m1);

        List<String> result = memberRepository.findUsernameList();

        assertThat(member.getUsername()).isEqualTo(result.get(0));
    }

    @Test
    void findMemberDto() {
        Team team = new Team("teamA");
        teamRepository.save(team);

        Member m1 = new Member("a", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println(dto);
        }
    }

    @Test
    void findByNames() {
        Member m1 = new Member("a", 10);
        Member m2 = new Member("b", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("a", "b"));
        for (Member member : result) {
            System.out.println(member);
        }
    }

    @Test
    void returnType() {
        Member m1 = new Member("a", 10);
        Member m2 = new Member("b", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> a = memberRepository.findListByUsername("a");
        System.out.println(a);
        Member a1 = memberRepository.findMemberByUsername("a");
        System.out.println(a1);
        Member a2 = memberRepository.findOptionalByUsername("a").get();
        System.out.println(a2);
    }

    @Test
    void pagingPage() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        // when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        // then
        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

//    @Test
//    void pagingSlice() {
//        // given
//        memberRepository.save(new Member("member1", 10));
//        memberRepository.save(new Member("member2", 10));
//        memberRepository.save(new Member("member3", 10));
//        memberRepository.save(new Member("member4", 10));
//        memberRepository.save(new Member("member5", 10));
//
//        int age = 10;
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
//
//        // when
//        Slice<Member> page = memberRepository.findByAge(age, pageRequest);
//
//        // then
//        List<Member> content = page.getContent();
////        long totalElements = page.getTotalElements();
//
//        assertThat(content.size()).isEqualTo(3);
////        assertThat(page.getTotalElements()).isEqualTo(5);
//        assertThat(page.getNumber()).isEqualTo(0);
////        assertThat(page.getTotalPages()).isEqualTo(2);
//        assertThat(page.isFirst()).isTrue();
//        assertThat(page.hasNext()).isTrue();
//    }
}