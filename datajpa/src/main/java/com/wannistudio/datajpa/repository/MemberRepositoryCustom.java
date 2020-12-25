package com.wannistudio.datajpa.repository;

import com.wannistudio.datajpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
