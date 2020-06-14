package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "gitproject", schema = "graduatedesign", catalog = "")
public class GitprojectEntity {
    private String projectName;
    private String userName;
    private String shortProjectName;
    private Timestamp addDate;

    @Id
    @Column(name = "project_name")
    public String getProjectName() {
        return projectName;
    }
    @Basic
    @Column(name = "user_Name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitprojectEntity that = (GitprojectEntity) o;
        return Objects.equals(projectName, that.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName);
    }


    @Basic
    @Column(name = "short_Project_Name")
    public String getShortProjectName() {
        return shortProjectName;
    }

    public void setShortProjectName(String shortProjectName) {
        this.shortProjectName = shortProjectName;
    }

    @Basic
    @Column(name = "add_Date")
    public Timestamp getAddDate() {
        return addDate;
    }

    public void setAddDate(Timestamp addDate) {
        this.addDate = addDate;
    }
}
