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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookId;

    private String bookName;

    @Column(unique = true)
    private String isbn;

    private Double price;
    private String language;

    @Enumerated(EnumType.STRING)
    private Category category;

    public static enum Category{
        FICTION,
        NON_FICTION


    }



}
