package com.example.demo.service;

import com.example.demo.model.userProjectName;

import java.io.*;
import java.util.ArrayList;

public class fileRead {

    public static String getFile()  {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("E:\\ideaDownload\\fileDemo\\temp"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str="";
            StringBuffer strBuffer=new StringBuffer();
            while (true) {
                try {
                    if ((str = in.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                strBuffer.append("<p>"+str+"</p>");
            }
//            System.out.println(strBuffer);
            return  strBuffer.toString();
    }

    public  static ArrayList<String> getFileList(String strPath){
        ArrayList<String> fileList=new ArrayList<>();
        File fileDir = new File(strPath);
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            for(File i:files){
                fileList.add(i.getName().replace("；","/"));
            }
        }
        return fileList;
}



    public static void main(String arg[]){
        fileRead.getFileList("E:\\ideaDownload\\fileDemo\\uZip");
    }

}
