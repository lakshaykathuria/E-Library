package com.Library.E_Library.repository;

import com.Library.E_Library.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {


    Optional<Member> findByUsername(String username);

    @Query("select m from Member m where m.username = ?1")
    Member findMemberByUsername(String username);

    @Query("select m from Member m where m.mobileNumber = ?1")
    Member findByMobileNumber(String mobileNumber);

    @Query("select m from Member m where m.email = ?1")
    Member findByEmail(String email);
}
