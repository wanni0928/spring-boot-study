package com.wannistudio.datajpa.repository;

import com.wannistudio.datajpa.dto.MemberDto;
import com.wannistudio.datajpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    // 경우에 따라 메소드명이 너무 길어진다.
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

//    @Query(name = "Member.findByUsername")
// 관례상 named query도 엔티티이름.메소드이름이 있는지 부터 확인한다. 즉 네이밍을 통일시키면 굳이 이 어노테이션을 쓸 필요가 없다.
    // 단점. 엔티티에서 혹은 리파지토리에서 오타를 냈다면, 해당 기능을 사용하기 전까지 오류를 확인할 수 없다.
    List<Member> findByUsername(@Param("username") String username);

    // 이 방식을 주로 사용한다.
    // 간편하기도 하고, 런타임 시점에서 버그를 잡아낼 수 있다.
    @Query("select m from Member  m where m.username = :username and m.age = :age")
    List<Member> findUserWithNameAndAge(@Param("username")String username, @Param("age")int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new com.wannistudio.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select  m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

//    Page<Member> findByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    // 업데이트 쿼리에 이 어노테이션이 없으면 InvalidDataAccessApiUsageException 오류가 뜬다.
    // clearAutomatically = true 를 선언해줌으로서, 영속성 컨텍스트를 entityManager 없이 초기화 해준다.
    @Query("update Member m set m.age = m.age + 1 where  m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    /*
    * 복잡한 페치조인을 '@EntityGraph' 로 쉽게 대처가능하다.
    * */
    // 간단한 페치조인은 이런 식으로 대체가능.
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
//    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);
}
