package com.Library.E_Library.entity;

import com.Library.E_Library.enums.IssueStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class IssueData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @NotNull
    @JsonIncludeProperties({"id", "firstName", "lastName"})
    private Member member;

    @ManyToOne
    @NotNull
    @JsonIncludeProperties({"bookId", "bookName"})
    private Book book;

    @Builder.Default()
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant createdAt = Instant.now();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Instant expirationDate;

    @NotNull
    private Double rentPaid;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private IssueStatus status = IssueStatus.ISSUED;


    public static enum IssueStatus{
        ISSUED,
        EXPIRED


    }
    public Instant calculateExpirationDate(int noOfDays){
        this.expirationDate = this.createdAt.plus(noOfDays, ChronoUnit.DAYS);
        return this.expirationDate;
    }

    public double calculateRentPaid(){
        this.rentPaid = this.book.getPrice()*0.05D;

        return this.rentPaid;
    }






}
