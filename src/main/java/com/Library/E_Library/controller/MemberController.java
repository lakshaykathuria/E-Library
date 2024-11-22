package com.Library.E_Library.controller;

import com.Library.E_Library.entity.Member;
import com.Library.E_Library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }



    @GetMapping("/getall")
    public ResponseEntity<List<Member>> getAllMember(){
        List<Member> allMember = this.memberService.listAllMember();
        return new ResponseEntity<>(allMember, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Member> getById(@RequestParam UUID memberId){
        Member memberById = this.memberService.getMemberById(memberId);
        return new ResponseEntity<>(memberById, HttpStatus.OK);
    }

    @PutMapping("/update/")
    public ResponseEntity<Member> updateById(@RequestBody Member member) {
        Member updatedMember = this.memberService.updateMember(member);

        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
