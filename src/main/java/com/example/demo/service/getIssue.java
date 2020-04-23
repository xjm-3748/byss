package com.example.demo.service;

import com.example.demo.Repository.issueRepository;
import com.example.demo.model.IssueEntity;
//import com.example.demo.model.issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class getIssue {
//    @Autowired
//    private issueRepository issueRepository;

//    public static void main(String[] args)  {
//        String useName="tikv";
//        String projectName="tikv";
//        getIssue g=new getIssue();
//        ArrayList<IssueEntity>aaa=(g.getIssueList(useName,projectName));
//        System.out.println(g.howManyPages(useName,projectName));
//    }

    public boolean downloadIssueLog(){
        return false;
    }
//    public  void  saveIssue(IssueEntity issue){
//        issueRepository.save(issue);
//    }

    public  int howManyPages(String username,String projectName){
        String  p="(data-total-pages=\")([0-9]*)" ;
        Pattern r = Pattern.compile(p);
        Matcher m;
        int re=1;
        try {
            //创建一个URL实例
            URL url = new URL("https://github.com/"+username+"/"+projectName+"/issues");
            try {
                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                //为字符输入流添加缓冲
                BufferedReader br = new BufferedReader(isr);
//                br.toString();
                String data = br.readLine();//读取数据
                while (data != null) {//循环读取数据
//                    System.out.println(data);//输出数据
                    m = r.matcher(data);
                    if (m.find()) {
//                        System.out.println(m.group(2));
                         re=Integer.valueOf(m.group(2));
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
        }finally {
            return re;
        }

    }
    public ArrayList<IssueEntity> getIssueList (String username,String projectName,int page){
        ArrayList<IssueEntity> re=new ArrayList<>();
        String  p="(<a class=\"d-block d-md-none position-absolute top-0 bottom-0 left-0 right-0\" aria-label=\"Link to Issue. )" +
                "(.*)" +
                "(href=\"/"+username+"/"+projectName+"/issues/)([0-9]*)";
        Pattern r = Pattern.compile(p);
        Matcher m;
        try {
            //创建一个URL实例
            URL url = new URL("https://github.com/"+username+"/"+projectName+"/issues?page="+page+"&q=is%3Aissue+is%3Aopen");
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
                        IssueEntity temp=new IssueEntity();
                        temp.setIssueId(username+projectName+m.group(4));
                        temp.setIssueTitle(m.group(2));
                        re.add(temp);
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



    public ArrayList<IssueEntity> getIssueList (String username,String projectName){
//        ArrayList<issue> re=new ArrayList<>();
//        String  p="(<a class=\"d-block d-md-none position-absolute top-0 bottom-0 left-0 right-0\" aria-label=\"Link to Issue. )" +
//                "(.*)" +
//                "(href=\"/"+username+"/"+projectName+"/issues/)([0-9]*)";
//        Pattern r = Pattern.compile(p);
//        Matcher m;
//        int page=0;
//        try {
//            //创建一个URL实例
//            URL url = new URL("https://github.com/"+username+"/"+projectName+"/issues?page="+page+"&q=is%3Aissue+is%3Aopen");
//            try {
//                //通过URL的openStrean方法获取URL对象所表示的自愿字节输入流
//                InputStream is = url.openStream();
//                InputStreamReader isr = new InputStreamReader(is, "utf-8");
//                //为字符输入流添加缓冲
//                BufferedReader br = new BufferedReader(isr);
//                String data = br.readLine();//读取数据
//                while (data != null) {//循环读取数据
////                    System.out.println(data);//输出数据
//                    m = r.matcher(data);
//                    if (m.find()) {
////                        System.out.println("fffffffffffff");
////                        String con=getIssueContent(username,projectName,m.group(4));
//                        re.add(new issue(m.group(2),m.group(4)));
//                    }
//                    data = br.readLine();
//                }
//                br.close();
//                isr.close();
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
        int pages=howManyPages(username,projectName);
        if(pages<=1)
            return getIssueList(username,projectName,1);
        else {
            ArrayList <IssueEntity> re=new ArrayList<>();
            for(int i=1;i<pages;i++){
                re.addAll(getIssueList(username, projectName,i));
            }
            return re;
        }
    }

    public IssueEntity  getIssueContent (String username,String projectName,String issueId){

        IssueEntity result=new IssueEntity();
        String Id=issueId.replaceAll(username,"").replace(projectName,"");
        String  p="<meta name=\"description\" content=\"" +
                "(.*)(\">)";
        Pattern r = Pattern.compile(p);
        Matcher m;
        String re="";
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
        result.setIssueContent(re);
        result.setUserName(username);
        result.setProjectName(projectName);
        result.setIssueId(issueId);

        return result;
    }


}
