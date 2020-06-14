package com.example.demo.service;

import io.kuy.infozilla.interact_interface.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class getTopTen {
    InteractInterface i=new InteractClass();


    public ArrayList<String> getFileList(String issue,String filePath){
        try {
            FileWriter fw = new FileWriter("temp/temp.txt");
            fw.write(issue);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> t=i.getSimilarTopTen("temp/temp.txt",filePath);
        return t;
    }

    public  String getColourIssue(){
        StringBuffer r=new StringBuffer();
        String s=i.getColoredReport("temp/temp.txt");
        String []arr=s.split("\r\n");
        for(String i:arr){
            r.append("<p>").append(i);
        }

        return r.toString();
    }


}
