package com.Library.E_Library.service;

import com.Library.E_Library.dto.Authdto;
import com.Library.E_Library.entity.Member;
import com.Library.E_Library.exceptions.IncorrectCredentialsExceptions;
import com.Library.E_Library.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member login (Authdto authdto){
        Optional<Member> memberOptional = this.memberRepository.findByUsername(authdto.getUsername());
        if(memberOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Member with username %s was not found", authdto.getUsername()));
        }

        Member member = memberOptional.get();

        if(!this.passwordEncoder.matches(authdto.getPassword(), member.getPassword())){
            log.info("Password is incorrect");
            throw new IncorrectCredentialsExceptions("Incorrect Password provided");
        }
        return member;
    }


    public Member signUp(Member member){
        log.info("Adding a Member");
        member.setPassword(this.passwordEncoder.encode(member.getPassword()));
        Member savedMember = this.memberRepository.save(member);
        log.info("Member saved with id: {}",savedMember.getMemberId());
        return savedMember;
    }

    public Member checkIfUsernameExists(String username) {
        return memberRepository.findMemberByUsername(username);
    }

    public Member checkIfMobileNumberExists(String mobileNumber) {
        return memberRepository.findByMobileNumber(mobileNumber);
    }
    public Member checkIfEmailExists(String email) {
        return memberRepository.findByEmail(email);
    }
}

