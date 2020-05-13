package com.example.demo.service;


import java.io.*;
import java.util.ArrayList;

public class fileRead {

    public static String getFile()  {

            return  getFile("E:\\ideaDownload\\fileDemo\\temp");
    }

    public static String getFile(String fileName)  {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String str="";
        StringBuilder strBuffer=new StringBuilder();
        while (true) {
            try {
                if ((str = in.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            strBuffer.append("<p>").append(str).append("</p>");
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
