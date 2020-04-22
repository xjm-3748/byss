package com.example.demo.Repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface issueRepository extends JpaRepository<IssueEntity,String> {
    @Transactional
    public List<IssueEntity> findAllByUserNameAndProjectName(String userName,String projectName);



}
