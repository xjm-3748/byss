package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class issue {
    @Id
    String userName;
    String title;
    String issueId;
    String content;
    public issue(){}

    public issue(String userName,String title,String issueId,String content){
        this.userName=userName;
        this.title=title;
        this.content=content;
        this.issueId=issueId;
    }
    public issue(String title,String issueId){
        this.title=title;
        this.issueId=issueId;
    }
    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssueId() {
        return issueId;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
