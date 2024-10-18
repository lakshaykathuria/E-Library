package com.Library.E_Library.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@With
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

    public static enum Status{
        ACTIVE,
        INACTIVE
    }
}
