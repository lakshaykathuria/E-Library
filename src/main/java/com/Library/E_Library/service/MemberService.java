package com.Library.E_Library.service;

import com.Library.E_Library.entity.Member;
import com.Library.E_Library.repository.MemberRepository;
import lombok.With;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member addMember(Member member){
        log.info("Adding a Member");
        Member savedMember = this.memberRepository.save(member);
        log.info("Member saved with id: {}",savedMember.getMemberId());
        return savedMember;
    }

    public List<Member> listAllMember(){
        return this.memberRepository.findAll();
    }

    public Member getMemberById(UUID memberId){
        Optional<Member> optionalMember = this.memberRepository.findById(memberId);
        return optionalMember.orElse(null);
    }

    public Member updateMember(Member member) {
        return memberRepository.findById(member.getMemberId())
                .map(existingMember -> {
                    existingMember.setEmail(member.getEmail());
                    existingMember.setFirstName(member.getFirstName());
                    existingMember.setLastName(member.getLastName());
                    existingMember.setMobileNumber(member.getMobileNumber());
                    existingMember.setStatus(member.getStatus());
                    return memberRepository.save(existingMember);
                })
                .orElse(null);
    }

}
