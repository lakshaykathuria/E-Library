package com.Library.E_Library.repository;

import com.Library.E_Library.entity.IssueData;
import com.Library.E_Library.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IssueDataRepository extends JpaRepository<IssueData, UUID> {


    @Query("select i from IssueData i where i.member.memberId = ?1")
    List<IssueData> findByMemberId(UUID memberId);

    @Modifying
    @Query("update IssueData i set i.status = ?1 where i.member = ?2 and i.expirationDate < ?3")
    int updateStatusByMemberAndExpirationDate(IssueData.IssueStatus status, Member member, Instant expirationDate);


}
