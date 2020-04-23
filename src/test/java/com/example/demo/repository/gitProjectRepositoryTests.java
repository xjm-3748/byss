package com.example.demo.repository;

import com.example.demo.Repository.gitProjectRepository;
import com.example.demo.model.GitprojectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class gitProjectRepositoryTests {

	@Resource
	private gitProjectRepository gitr;

	@Test
	public void testSave() {

		GitprojectEntity g=new GitprojectEntity();
//		g.setProjectName("this is project Name");
//		g.setUserName("this is userName");
		g.setProjectName("lalalalalala");
		gitr.save(g);

	}
//	@Test
//	public void testfindAll() {
//		List<IssueEntity> ls =issueRepository.findAllByUserNameAndProjectName("thisIsUserName","thisProjectName");
//		Assert.assertEquals(ls.size(),1);
//	}
//	@Test
//	public void testDelete() {
//		issueRepository.deleteById("thisIsIssueId222");
//	}

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