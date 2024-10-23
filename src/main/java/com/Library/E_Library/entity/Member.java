package com.Library.E_Library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID memberId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;

    private String role;

    @Column(unique = true)
    private String mobileNumber;

    @Email
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    public static enum Status{
        ACTIVE,
        INACTIVE
    }
}
