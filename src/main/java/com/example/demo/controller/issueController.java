package com.example.demo.controller;

import com.example.demo.Repository.gitProjectRepository;
import com.example.demo.Repository.issueRepository;
import com.example.demo.model.GitprojectEntity;
import com.example.demo.model.IssueEntity;
import com.example.demo.model.fileReaderEntity;
import com.example.demo.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import static com.example.demo.service.UZipFile.unZipFiles;

@Controller
@CrossOrigin
@RequestMapping("/")
public class issueController {
	String path="E:\\ideaDownload\\fileDemo\\";
	@Resource
	getIssue getIssueService;
//	issuemessageService issuemessageService=new issuemessageServiceImpl();
	@Resource
	gitProjectRepository gitProjectRepositorythis;
	@Resource
	issueRepository  issueSql;
	@Resource
	getTopTen getTopTenService;

	@GetMapping("/a.html")
	public ModelAndView list() {
		return index();
	}

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView modelAndView=new ModelAndView("a");
		List<GitprojectEntity> alread=gitProjectRepositorythis.findAll();

		modelAndView.addObject("alreadyFileList",alread);
		return modelAndView;
	}


	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response) {
		String userName=request.getParameter("userName");
		String projectName=request.getParameter("projectName");
//		System.out.println("save这里AAAAAA");
		request.getSession().setAttribute("userName",userName);
		request.getSession().setAttribute("projectName",projectName);
	}
//	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/setIssueId",method = RequestMethod.POST)
	@ResponseBody
	public void setIssueId(HttpServletRequest request) {
		String issueId=request.getParameter("issueId");
		String issueTitle=request.getParameter("issueTitle");

		request.getSession().setAttribute("issueId",issueId);
		request.getSession().setAttribute("issueTitle",issueTitle);
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
		try {
			fileDownload.downLoadFromUrl("https://github.com/"
							+userName+"/"+projectName+"/archive/master.zip",userName+"；"+projectName+".zip"
					,"E:\\ideaDownload\\fileDemo\\");
		} catch (IOException e) {
			e.printStackTrace();
		}
        File zipFile = new File("E:\\ideaDownload\\fileDemo\\"+userName+"；"+projectName+".zip");
        String path = "E:\\ideaDownload\\fileDemo\\uZip\\"+userName+"；"+projectName+"\\";
        File newFile=new File(path);
        if(!newFile.exists()){
			newFile.mkdirs();
		}
        try {
            unZipFiles(zipFile, path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<IssueEntity> ls=getIssueService.getIssueList(userName,projectName);

        for(IssueEntity i:ls){
        	i=getIssueService.getIssueContent(i);
			issueSql.save(i);
		}
//		issuemessageService
		GitprojectEntity gitprojectEntity=new GitprojectEntity();
//		gitprojectEntity.setUserName(userName);
		gitprojectEntity.setProjectName(userName+"/"+projectName);
		gitprojectEntity.setUserName(userName);
		gitprojectEntity.setShortProjectName(projectName);
//		Timestamp t=new Timestamp(System.nanoTime());
		gitprojectEntity.setAddDate( new Timestamp(System.currentTimeMillis()));

        gitProjectRepositorythis.save(gitprojectEntity);
        System.out.println(userName + projectName+"downloadAndUnzip");
	}

	@RequestMapping(value ="/downloading",method = RequestMethod.GET)
	public ModelAndView downloading(HttpServletRequest request, HttpServletResponse response){
//		String userName=(String) request.getSession().getAttribute("userName");
//		String projectName=(String) request.getSession().getAttribute("projectName");
//		System.out.println(userName + projectName);
		return  new ModelAndView( "downloading" );
	}


	@RequestMapping(value = "/existThisProject",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> existThisProject(HttpServletRequest request, HttpServletResponse response){
		String userName=(String) request.getSession().getAttribute("userName");
		String projectName=(String) request.getSession().getAttribute("projectName");

		Map<String,Object> map =new HashMap<String,Object>();
		GitprojectEntity g=new GitprojectEntity();
		g.setProjectName(userName+projectName);
		if(gitProjectRepositorythis.existsById(userName+"/"+projectName)){
			map.put("result","1");
		}else
			map.put("result","0");
		return  map;
	}

	@RequestMapping("/issue")
	public ModelAndView issueSet(HttpServletRequest request){
        String userName=(String) request.getSession().getAttribute("userName");
        String projectName=(String) request.getSession().getAttribute("projectName");
        List<IssueEntity> issueList =issueSql.findAllByUserNameAndProjectName(userName,projectName);
//				getIssueService.getIssueList(userName,projectName);

        ModelAndView modelAndView = new ModelAndView( "fileShow" );
        modelAndView.addObject( "issueList", issueList );
        modelAndView.addObject( "nameOfProject", userName+projectName );
        return  modelAndView;
	}


	@RequestMapping("/issueDetail")
	public ModelAndView issueDetail(HttpServletRequest request){
		String issueId =(String) request.getSession().getAttribute("issueId");

		String userName=(String) request.getSession().getAttribute("userName");
		String projectName=(String) request.getSession().getAttribute("projectName");
        IssueEntity i=issueSql.findById(issueId).get();
		request.getSession().setAttribute("issueMessage",i);

		String filePath=path+"uZip\\"+userName+"；"+projectName;

		ArrayList<String> temp=getTopTenService.getFileList(i.getIssueContent(),filePath);

		ArrayList<String> fileList=new ArrayList<>();
		for(String it:temp){
			fileList.add(it.substring(filePath.length()+1));
		}
		ModelAndView mav=new ModelAndView( "issueDetail" );
		mav.addObject("fileList",fileList);
		return mav;
	}


	@RequestMapping("/readFile")
	public ModelAndView readFile(HttpServletRequest request){
		String fileName=(String) request.getSession().getAttribute("fileName");
        IssueEntity IssueE=(IssueEntity) request.getSession().getAttribute("issueMessage");
//		String userName=(String) request.getSession().getAttribute("userName");
//		String projectName=(String) request.getSession().getAttribute("projectName");

		String contentColour=getTopTenService.getColourIssue();
		IssueE.setIssueContent(contentColour);
		System.out.println(fileName);
        String[] fileLastName = fileName.split("\\\\");

		String code=	fileRead.getFile
                ("result\\colored_class\\"+fileLastName[fileLastName.length-1]);
		code=code.replaceAll("\\$natural\\$","<span class=\"natural\">").replaceAll("\\$\\$","</span>");

		code=code.replaceAll("\\$stack\\$","<span class=\"stack\">");
		code=code.replaceAll("\\$code\\$","<span class=\"code\">");


		ModelAndView mav=new ModelAndView( "readFile" );
		mav.addObject("code",code);
        mav.addObject("issueShow",IssueE);
        return mav;
	}

	@CrossOrigin
	@RequestMapping(value ="/readColorFileContent",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public fileReaderEntity  getColorFile(
			@RequestParam("fileName") String fileName,
			@RequestParam("issueId") String issueId){

		String contentColour=getTopTenService.getColourIssue();
		contentColour=contentColour.replaceAll("\\$natural\\$","<span class=\"natural\">").replaceAll("\\$\\$","</span>");

		contentColour=contentColour.replaceAll("\\$stack\\$","<span class=\"stack\">");
		contentColour=contentColour.replaceAll("\\$code\\$","<span class=\"code\">");


		System.out.println(fileName);
		String[] fileLastName = fileName.split("\\\\");
		String code=	fileRead.getFileWithLine
				("result\\colored_class\\"+fileLastName[fileLastName.length-1]);
		code=code.replaceAll("\\$natural\\$","<span class=\"natural\">").replaceAll("\\$\\$","</span>");

		code=code.replaceAll("\\$stack\\$","<span class=\"stack\">");
		code=code.replaceAll("\\$code\\$","<span class=\"code\">");



		fileReaderEntity f=new fileReaderEntity();
		f.setFileName(contentColour);
		f.setFileContent(code);
		return f;
	}


	@CrossOrigin
	@RequestMapping(value ="/jsonIssueList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<IssueEntity>jsonIssueList(
			@RequestParam("userName") String userName,
			@RequestParam("projectName") String projectName
	){
		System.out.println("issue huo qu");

		return  issueSql.findAllByUserNameAndProjectName(userName,projectName);
	}


	@CrossOrigin
	@RequestMapping(value ="/jsonGetProjectList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<GitprojectEntity>jsonGetProjectList(
	){
		return  gitProjectRepositorythis.findAll();

	}


	@CrossOrigin
	@RequestMapping(value = "/vueDownload",method = RequestMethod.GET)
	@ResponseBody
	public void vueDownload(@RequestParam("userName") String userName,
							@RequestParam("projectName") String projectName) {
		System.out.println("xiazai ");
//		try {
//			fileDownload.downLoadFromUrl("https://github.com/"
//							+userName+"/"+projectName+"/archive/master.zip",userName+"；"+projectName+".zip"
//					,"E:\\ideaDownload\\fileDemo\\");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		File zipFile = new File("E:\\ideaDownload\\fileDemo\\"+userName+"；"+projectName+".zip");
//		String path = "E:\\ideaDownload\\fileDemo\\uZip\\"+userName+"；"+projectName+"\\";
//		File newFile=new File(path);
//		if(!newFile.exists()){
//			newFile.mkdirs();
//		}
//		try {
//			unZipFiles(zipFile, path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ArrayList<IssueEntity> ls=getIssueService.getIssueList(userName,projectName,2);
		for(IssueEntity i:ls){
			i=getIssueService.getIssueContent(i);
			issueSql.save(i);
		}
		GitprojectEntity gitprojectEntity=new GitprojectEntity();
		gitprojectEntity.setProjectName(userName+"/"+projectName);
		gitprojectEntity.setUserName(userName);
		gitprojectEntity.setShortProjectName(projectName);
		gitprojectEntity.setAddDate( new Timestamp(System.currentTimeMillis()));
		gitProjectRepositorythis.save(gitprojectEntity);
		System.out.println(userName + projectName+"downloadAndUnzip");
	}

	@CrossOrigin
	@RequestMapping(value = "/getTopTenFileList",method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<String>  getTopTenFileList(@RequestParam("userName") String userName,
										  @RequestParam("issueId") String issueId,
										  @RequestParam("projectName") String projectName){
		System.out.println("wenjian liebioa ");
		IssueEntity i=issueSql.findById(issueId).get();
		String filePath=path+"uZip\\"+userName+"；"+projectName;

		ArrayList<String> temp=getTopTenService.getFileList(i.getIssueContent(),filePath);

		ArrayList<String> fileList=new ArrayList<>();
		for(String it:temp){
			fileList.add(it.substring(filePath.length()+1));
		}
//		String e=fileList.get(0);
//		e="<span style=\"color: red\">"+e+"";
//		fileList.set(0,e);
		return fileList;
	}

	@CrossOrigin
	@RequestMapping(value = "/getIssueById",method = RequestMethod.GET)
	@ResponseBody
	public IssueEntity  getFileContent(@RequestParam("issueId") String issueId){

		System.out.println("issue  fanhui ");
		IssueEntity i=issueSql.findById(issueId).get();
		return i;
	}

	@CrossOrigin
	@RequestMapping(value = "/getGithubRepositories ",method = RequestMethod.GET)
	@ResponseBody
	public IssueEntity  getGithubRepositories (@RequestParam("issueId") String issueId){
//		GithubRepositories()

		IssueEntity i=issueSql.findById(issueId).get();
		return i;
	}


}

