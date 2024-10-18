package com.Library.E_Library.controller;

import com.Library.E_Library.entity.Member;
import com.Library.E_Library.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@SpringBootTest
public class MemberControllerTest {

    @MockBean
    private MemberService memberService;

    private final MemberController memberController;

    @Autowired
    public MemberControllerTest(MemberController memberController) {
        this.memberController = memberController;
    }

    static final Member member = Member.builder()
            .email("test@gmail.com")
            .memberId(UUID.randomUUID())
            .firstName("Test")
            .lastName("Tester")
            .mobileNumber("5674567")
            .status(Member.Status.ACTIVE)
            .build();


    @Test
    void addMember_whenValidMemberIsPassed_shouldReturnCREATED(){

        Mockito.when(this.memberService.addMember(member)).thenReturn(member);

        ResponseEntity<Member> responseEntity = this.memberController.addMember(member);

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertEquals(member, responseEntity.getBody());
    }
}
