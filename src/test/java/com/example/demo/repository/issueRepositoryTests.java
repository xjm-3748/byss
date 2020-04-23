package com.example.demo.repository;

import com.example.demo.Repository.issueRepository;
import com.example.demo.model.IssueEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class issueRepositoryTests {

	@Resource
	private issueRepository issueRepository;

//	@Test
//	public void testSave() {
//		IssueEntity  issueEntity=new IssueEntity();
//		issueEntity.setIssueContent("thisIsContent");
//		issueEntity.setIssueId("thisIsIssueId222");
//		issueEntity.setProjectName("thisProjectName");
//		issueEntity.setUserName("thisIsUserName");
//		issueEntity.setIssueTitle("thisIsTitle");
//		issueRepository.save(issueEntity);
//	}
//	@Test
//	public void testfindAll() {
//		List<IssueEntity> ls =issueRepository.findAllByUserNameAndProjectName("thisIsUserName","thisProjectName");
//		Assert.assertEquals(ls.size(),1);
//	}
//	@Test
//	public void testDelete() {
//		issueRepository.deleteById("thisIsIssueId222");
//	}
	@Test
	public void testfind() {
		IssueEntity ls =issueRepository.findById("HotBitmapGGbilibili-android-client57").get();

		System.out.println("aaaa");
		//		Assert.assertEquals(ls.size(),1);
	}
//	@Test
//	public void testBaseQuery() {
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
//		String formattedDate = dateFormat.format(date);
//		User user=new User("ff", "ff123456","ff@126.com", "ff",  formattedDate);
//		userRepository.findAll();
//		userRepository.findById(3L);
//		userRepository.save(user);
//		user.setId(2L);
//		try {
//		userRepository.delete(user);
//		}catch (Exception e){
//
//		}
//
//		userRepository.count();
//		userRepository.existsById(3L);
//	}
//
//	@Test
//	public void testCustomSql() {
//		userRepository.modifyById("neo",3L);
//		userRepository.deleteById(3L);
//		userRepository.findByEmail("ff@126.com");
//	}
//
//
//	@Test
//	public void testPageQuery()  {
//		int page=1,size=2;
//		Sort sort = new Sort(Sort.Direction.DESC, "id");
//		Pageable pageable = PageRequest.of(page, size, sort);
//		userRepository.findALL(pageable);
//		userRepository.findByNickName("aa", pageable);
//	}

}