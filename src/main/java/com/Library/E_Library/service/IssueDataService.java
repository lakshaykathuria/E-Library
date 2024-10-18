package com.Library.E_Library.service;

import com.Library.E_Library.dto.IssueDataDTO;
import com.Library.E_Library.entity.Book;
import com.Library.E_Library.entity.IssueData;
import com.Library.E_Library.entity.Member;
import com.Library.E_Library.repository.IssueDataRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class IssueDataService {
    private final IssueDataRepository issueDataRepository;
    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    public IssueDataService(IssueDataRepository issueDataRepository, BookService bookService, MemberService memberService) {
        this.issueDataRepository = issueDataRepository;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public IssueData addIssueData(IssueDataDTO issueDataDTO){
        Book book = this.bookService.getBookById(issueDataDTO.getBookId());
        Member member = this.memberService.getMemberById(issueDataDTO.getMemberId());

        if(book==null|| member==null){
            throw new RuntimeException();
        }
        IssueData issueData = IssueData.builder()
                .book(book)
                .member(member)
                .build();
        return this.addIssueData(issueData);
    }


    public IssueData addIssueData(IssueData issueData) {
        log.info ( "Saving a new Issue Data" );
        issueData.calculateRentPaid ();
        issueData.calculateExpirationDate (15);
        IssueData savedIssueData = this.issueDataRepository.save ( issueData );
        log.info ( "Saved a new issue data with ID: {}, for book ID: {} by member ID: {}",
                savedIssueData.getId (), savedIssueData.getBook ().getBookId (), savedIssueData.getMember ().getMemberId () );
        return issueData;
    }

    public List<IssueData> getIssueDataByMemberId(UUID memberId){
        List<IssueData> issueDataList = this.issueDataRepository.findByMemberId(memberId);
        log.info("Found {} issue data for member Id: {} ",issueDataList.size(), memberId);
        return issueDataList;
    }


    @Transactional
    public List<IssueData> updateIssueDataByMemberId(UUID memberId) {
        Member member = this.memberService.getMemberById(memberId);
        if (member == null) {
            throw new RuntimeException("Member not found");
        }

        Instant expirationDate = Instant.now().minusSeconds(15 * 24 * 60 * 60); // 15 days in seconds

        int updatedRecords = this.issueDataRepository.updateStatusByMemberAndExpirationDate(
                IssueData.IssueStatus.EXPIRED, member, expirationDate);

        log.info("Updated {} issue data records for member Id: {} to EXPIRED", updatedRecords, memberId);

        List<IssueData> issueDataList = this.issueDataRepository.findByMemberId(memberId);
        log.info("Found {} issue data for member Id: {} ", issueDataList.size(), memberId);
        return issueDataList;
    }
}
