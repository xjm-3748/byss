package com.example.demo.model;

public class issue {
    String title;
    String id;
    String content;

    public issue(String title,String id,String content){
        this.title=title;
        this.content=content;
        this.id=id;
    }
    public issue(String title,String id){
        this.title=title;
        this.id=id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
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
