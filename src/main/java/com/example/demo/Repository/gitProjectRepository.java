package com.example.demo.Repository;

import com.example.demo.model.GitprojectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface gitProjectRepository extends JpaRepository<GitprojectEntity, String> {
    @Transactional
    public List<GitprojectEntity> findAllByUserName(String userName);
}