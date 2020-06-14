//package com.example.demo.service;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class githubApi {
//    public static String loadJson (String url) {
//        StringBuilder json = new StringBuilder();
//        try {
//            URL urlObject = new URL(url);
//            URLConnection uc = urlObject.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//            String inputLine = null;
//            while ( (inputLine = in.readLine()) != null) {
//                json.append(inputLine);
//            }
//            in.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return json.toString();
//    }
//    public static void main(String[] args) {
//        String url = "https://api.github.com/users/xjm-3748/repos";
////        String url = "http://www.kuaidi100.com/query?type=yunda&postid=1201386764793";
//        String json = loadJson(url);
//        JSONObject j= JSON.parseObject(json);
//
//        System.out.println(json);
//    }
//
//}
