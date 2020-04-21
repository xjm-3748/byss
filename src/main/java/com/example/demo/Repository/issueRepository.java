package com.example.demo.Repository;

import com.example.demo.model.issue;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface issueRepository extends JpaRepository<issue,Long> {


}
