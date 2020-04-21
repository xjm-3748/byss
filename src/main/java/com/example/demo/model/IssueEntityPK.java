//package com.example.demo.model;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import java.io.Serializable;
//import java.util.Objects;
//
//public class IssueEntityPK implements Serializable {
//    private String userName;
//    private String projectName;
//
//    @Column(name = "userName")
//    @Id
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @Column(name = "projectName")
//    @Id
//    public String getProjectName() {
//        return projectName;
//    }
//
//    public void setProjectName(String projectName) {
//        this.projectName = projectName;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        IssueEntityPK that = (IssueEntityPK) o;
//        return Objects.equals(userName, that.userName) &&
//                Objects.equals(projectName, that.projectName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(userName, projectName);
//    }
//}
