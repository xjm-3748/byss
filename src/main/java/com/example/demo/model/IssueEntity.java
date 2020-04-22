package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "issue", schema = "graduatedesign", catalog = "")
public class IssueEntity {
    private String issueId;
    private String issueTitle;
    private String issueContent;
    private String projectName;
    private String userName;
    public  IssueEntity(){

    }

    @Id
    @Column(name = "issueId")
    public String getIssueId() {
        return issueId;
    }

    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    @Basic
    @Column(name = "issueTitle")
    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    @Basic
    @Column(name = "issueContent")
    public String getIssueContent() {
        return issueContent;
    }

    public void setIssueContent(String issueContent) {
        this.issueContent = issueContent;
    }

    @Basic
    @Column(name = "projectName")
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "userName")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueEntity that = (IssueEntity) o;
        return Objects.equals(issueId, that.issueId) &&
                Objects.equals(issueTitle, that.issueTitle) &&
                Objects.equals(issueContent, that.issueContent) &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(userName, that.userName);
    }



    @Override
    public int hashCode() {
        return Objects.hash(issueId, issueTitle, issueContent, projectName, userName);
    }
}
