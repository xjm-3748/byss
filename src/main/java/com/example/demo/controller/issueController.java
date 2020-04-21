
package com.example.demo.controller;

import com.example.demo.model.issue;
import com.example.demo.service.fileDownload;
import com.example.demo.service.fileRead;
import com.example.demo.service.getIssue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.demo.service.UZipFile.unZipFiles;

@Controller
@RequestMapping("/")
public class issueController {
	String path="E:\\ideaDownload\\fileDemo\\";
	getIssue getIssueService=new getIssue();


//	@GetMapping("/a.html")
//	public ModelAndView list() {
//		return new ModelAndView("a");
//	}
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView("a");
		modelAndView.addObject("alreadyFileList",fileRead.getFileList(path+"uZip"));

		return modelAndView;
	}


	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response) {
		String userName=request.getParameter("userName");
		String projectName=request.getParameter("projectName");
		System.out.println("save这里AAAAAA");
		request.getSession().setAttribute("userName",userName);
		request.getSession().setAttribute("projectName",projectName);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/setIssueId",method = RequestMethod.POST)
	@ResponseBody
	public void setIssueId(HttpServletRequest request) {
		String issueId=request.getParameter("issueId");
		request.getSession().setAttribute("issueId",issueId);
	}

	@RequestMapping(value = "/setFileName",method = RequestMethod.POST)
	@ResponseBody
	public void setFileName(HttpServletRequest request) {
		String fileName=request.getParameter("fileName");
		request.getSession().setAttribute("fileName",fileName);
	}





	@RequestMapping(value = "/downloadAndUnzip",method = RequestMethod.POST)
	@ResponseBody
	public void downloadAndUnzip(HttpServletRequest request, HttpServletResponse response) {
		String userName=(String) request.getSession().getAttribute("userName");
		String projectName=(String) request.getSession().getAttribute("projectName");

//		try {
//			fileDownload.downLoadFromUrl("https://github.com/"
//							+userName+"/"+projectName+"/archive/master.zip",userName+"；"+projectName+".zip"
//					,"E:\\ideaDownload\\fileDemo\\");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//        File zipFile = new File("E:\\ideaDownload\\fileDemo\\"+userName+"；"+projectName+".zip");
//        String path = "E:\\ideaDownload\\fileDemo\\uZip\\"+userName+"；"+projectName+"\\";
//        File newFile=new File(path);
//
//        if(!newFile.exists()){
//			newFile.mkdirs();
//		}
//        try {
//            unZipFiles(zipFile, path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(userName + projectName);
	}

	@RequestMapping(value ="/downloading",method = RequestMethod.GET)
	public ModelAndView downloading(HttpServletRequest request, HttpServletResponse response){
//		String userName=(String) request.getSession().getAttribute("userName");
//		String projectName=(String) request.getSession().getAttribute("projectName");
//		System.out.println(userName + projectName);
		return  new ModelAndView( "downloading" );
	}


	@RequestMapping("/issue")
	public ModelAndView issueSet(HttpServletRequest request){
        String userName=(String) request.getSession().getAttribute("userName");
        String projectName=(String) request.getSession().getAttribute("projectName");
        ArrayList<issue> issueList =getIssueService.getIssueList(userName,projectName);
        ModelAndView modelAndView = new ModelAndView( "fileShow" );
        modelAndView.addObject( "issueList", issueList );
        modelAndView.addObject( "nameOfProject", userName+projectName );
        return  modelAndView;
	}


	@RequestMapping("/issueDetail")
	public ModelAndView issueDetail(HttpServletRequest request){

		String userName=(String) request.getSession().getAttribute("userName");
		String projectName=(String) request.getSession().getAttribute("projectName");
		String issueId =(String) request.getSession().getAttribute("issueId");
		ArrayList<issue> ls=getIssueService.getIssueList(userName,projectName);
		for(issue i :ls){
			if(i.getIssueId().equals(issueId)){
				i.setContent(getIssueService.getIssueContent(userName,projectName,issueId));
				request.getSession().setAttribute("issueMessage",i);
				break;
			}
		}
		System.out.println("issueDetail");
		//todo 找到对应文件列表
		ArrayList<String>fileList=new ArrayList<>();
		fileList.add("fileA.txt");
		fileList.add("eeee.txt");
		fileList.add("bbbb.txt");
		fileList.add("ccccc.txt");
		fileList.add("EEEE.txt");
		ModelAndView mav=new ModelAndView( "issueDetail" );
		mav.addObject("fileList",fileList);
		return mav;
	}

	@RequestMapping("/readFile")
	public ModelAndView readFile(HttpServletRequest request){

		String fileName=(String) request.getSession().getAttribute("fileName");
		System.out.println(fileName);
		//todo 写入到temp中去

		String code=fileRead.getFile();
		System.out.println("issueDetail");
		ModelAndView mav=new ModelAndView( "readFile" );
		mav.addObject("code",code);
		return mav;
	}



}

