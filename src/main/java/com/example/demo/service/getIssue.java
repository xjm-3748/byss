package com.example.demo.service;

import com.example.demo.model.issue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class getIssue {
    public static void main(String[] args)  {
        String useName="HotBitmapGG" +
                "";
        String projectName="bilibili-android-client";

        getIssue g=new getIssue();
        ArrayList<issue>aaa=(g.getIssueList(useName,projectName));
        System.out.println(aaa);
    }

    public ArrayList<issue> getIssueList (String username,String projectName){
        ArrayList<issue> re=new ArrayList<>();
        String  p="(<a class=\"d-block d-md-none position-absolute top-0 bottom-0 left-0 right-0\" aria-label=\"Link to Issue. )" +
                "(.*)" +
                "(href=\"/"+username+"/"+projectName+"/issues/)([0-9]*)";


        Pattern r = Pattern.compile(p);
        Matcher m;
        try {
            //创建一个URL实例
            URL url = new URL("https://github.com/"+username+"/"+projectName+"/issues");
            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                while (data != null) {//循环读取数据
//                    System.out.println(data);//输出数据

                    m = r.matcher(data);
                    if (m.find()) {
//                        System.out.println("fffffffffffff");
//                        String con=getIssueContent(username,projectName,m.group(4));
                        re.add(new issue(m.group(2),m.group(4)));
                    }
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return re;

    }

    public String  getIssueContent (String username,String projectName,String issueId){

//        wait(1000);
        String re="";
        String  p="<meta name=\"description\" content=\"" +
                "(.*)(\">)";
        Pattern r = Pattern.compile(p);
        Matcher m;
        try {
            //创建一个URL实例
            URL url = new URL("https://github.com/"+username
                    +"/"+projectName+"/issues/"+issueId);
            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();//读取数据
                while (data != null) {//循环读取数据
//                    System.out.println(data);//输出数据
                    m = r.matcher(data);
                    if (m.find()) {
//                        System.out.println(m.group(1));
                        re+=m.group(1);
                    }
                    data = br.readLine();
                }
                br.close();
                isr.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return re;
    }


}
