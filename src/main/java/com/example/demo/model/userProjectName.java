package com.example.demo.model;

public class userProjectName {

    String userName;
    String projectName;


    public userProjectName(String userName,String projectName){
        this.projectName=projectName;
        this.userName=userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        projectName = projectName;
    }

    public String getUserName() {
        return userName;
    }

}
