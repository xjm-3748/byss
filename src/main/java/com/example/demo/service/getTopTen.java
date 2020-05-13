package com.example.demo.service;

import io.kuy.infozilla.interact_interface.*;

import java.io.File;
import java.util.ArrayList;

public class getTopTen {
    public static void main(String args[]){
        InteractInterface i=new InteractClass();
        ArrayList<String> aaa=i.getSimilarTopTen("C:\\Users\\Administrator\\OneDrive\\桌面\\毕设\\new.txt","E:\\ideaDownload\\fileDemo\\uZip\\HotBitmapGG；bilibili-android-client");
        aaa.add("");
    }
}
