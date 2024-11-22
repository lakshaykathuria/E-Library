package com.Library.E_Library.controller;

import com.Library.E_Library.dto.Authdto;
import com.Library.E_Library.entity.Member;
import com.Library.E_Library.exceptions.IncorrectCredentialsExceptions;
import com.Library.E_Library.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    private ResponseEntity<Member> login(@RequestBody Authdto authdto){
        try{
            Member member = this.authenticationService.login(authdto);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }
        catch (UsernameNotFoundException | IncorrectCredentialsExceptions exception){

            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody Member member) {
        if (member.getPassword() == null || member.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password must not be empty");
        }


        Member usernameExists = authenticationService.checkIfUsernameExists(member.getUsername());
        Member emailExists = authenticationService.checkIfEmailExists(member.getEmail());
        Member mobileNumberExists = authenticationService.checkIfMobileNumberExists(member.getMobileNumber());
        if (usernameExists!=null) {
            return ResponseEntity.badRequest().body("Username already exists. Please choose a different username.");
        }

        if (mobileNumberExists!=null) {
            return ResponseEntity.badRequest().body("Mobile Number already exists. Please choose a different Mobile Number.");
        }

        if (emailExists!=null) {
            return ResponseEntity.badRequest().body("Email already exists. Please choose a different Email.");
        }
        Member savedMember = this.authenticationService.signUp(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }
}
