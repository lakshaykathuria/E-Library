package com.Library.E_Library.dto;

import lombok.*;

@Data
@Builder
@With
@NoArgsConstructor
@AllArgsConstructor
public class Authdto {

    private String username;
    private String password;
}
