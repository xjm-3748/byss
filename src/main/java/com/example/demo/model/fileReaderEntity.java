package com.example.demo.model;

public class fileReaderEntity {
    private String fileName;
    private String fileContent;

    public String getFileContent() {
        return fileContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
