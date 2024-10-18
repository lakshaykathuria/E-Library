package com.Library.E_Library.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID memberId;

    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    private static enum Status{
        ACTIVE,
        INACTIVE
    }
}
