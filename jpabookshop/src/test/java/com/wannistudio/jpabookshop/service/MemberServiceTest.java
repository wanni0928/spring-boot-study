package com.wannistudio.jpabookshop.service;

import com.wannistudio.jpabookshop.domain.Address;
import com.wannistudio.jpabookshop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void join() throws Exception {
        //given
        Member member = new Member();
        member.setName("wanni");

        //when
        Long savedId = memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberService.findOne(savedId));
    }

    @Test
    void checkDuplicate() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("wanni");
        Member member2 = new Member();
        member2.setName("wanni");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}