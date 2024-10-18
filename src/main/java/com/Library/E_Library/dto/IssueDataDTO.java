package com.Library.E_Library.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class IssueDataDTO {

    private UUID bookId;
    private UUID memberId;
}

