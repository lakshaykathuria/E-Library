package com.Library.E_Library.service;

import com.Library.E_Library.ELibraryApplication;
import com.Library.E_Library.entity.Book;
import com.Library.E_Library.entity.Member;
import com.Library.E_Library.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.PrimitiveIterator;
import java.util.UUID;

@SpringBootTest(classes = ELibraryApplication.class)
public class MemberServiceTest {

    @MockBean
    private MemberRepository memberRepository;

    private final MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    static Member member = Member.builder()
            .memberId(UUID.randomUUID())
            .email("Test@email.com")
            .mobileNumber("56747564")
            .firstName("Test")
            .lastName("Tester")
            .status(Member.Status.ACTIVE)
            .build();

    @Test
    void addMember_whenValidMemberIsPassed_shouldReturnSavedMember(){

        Mockito.when(this.memberRepository.save(member)).thenReturn(member);

        Member member1 = this.memberService.addMember(member);

        Assertions.assertEquals(member,member1);
    }
}
