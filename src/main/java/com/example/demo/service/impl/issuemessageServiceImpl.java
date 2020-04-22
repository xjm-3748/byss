package com.example.demo.service.impl;


import com.example.demo.Repository.issueRepository;
import com.example.demo.model.IssueEntity;
import com.example.demo.service.issuemessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class issuemessageServiceImpl implements issuemessageService {
    @Autowired
    private  issueRepository issueRepository;

    @Transactional
    @Override
    public List<IssueEntity> findIssueByUserNameAndProjectName(String userName, String projectName) {
        return issueRepository.findAllByUserNameAndProjectName(userName,projectName);
    }
    @Transactional
    public void  save(IssueEntity issueEntity){
        issueRepository.save(issueEntity);
    }

    @Transactional
    public void deleteById(String id){
        issueRepository.deleteById(id);
    }



}
