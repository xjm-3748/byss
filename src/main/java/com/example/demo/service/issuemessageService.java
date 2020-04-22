package com.example.demo.service;

import com.example.demo.model.IssueEntity;

import java.util.List;

public interface issuemessageService {

    public List<IssueEntity> findIssueByUserNameAndProjectName(String userName,String projectName);

    public void  save(IssueEntity issueEntity);
    public void deleteById(String id);
}
