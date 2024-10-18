package com.Library.E_Library.controller;

import com.Library.E_Library.dto.IssueDataDTO;
import com.Library.E_Library.entity.IssueData;
import com.Library.E_Library.service.IssueDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/issue_data")
public class IssueDataController {

    private final IssueDataService issueDataService;

    @Autowired
    public IssueDataController(IssueDataService issueDataService) {
        this.issueDataService = issueDataService;
    }

    @PostMapping
    public ResponseEntity<IssueData> addIssueData(@RequestBody IssueDataDTO issueDataDTO){
        IssueData issueData = this.issueDataService.addIssueData(issueDataDTO);
        return new ResponseEntity<>(issueData,HttpStatus.OK);

//        IssueData savedData = this.issueDataService.addIssueData(issueData);
//        return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IssueData>> getIssueDataByMemberId(@RequestParam UUID memberId){
        List<IssueData> issueDataList = this.issueDataService.getIssueDataByMemberId(memberId);
        return new ResponseEntity<>(issueDataList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<List<IssueData>> updateById(@RequestParam UUID memberId) {
        List<IssueData> updatedIssueData = this.issueDataService.updateIssueDataByMemberId(memberId);

        if (updatedIssueData != null) {
            return new ResponseEntity<>(updatedIssueData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
