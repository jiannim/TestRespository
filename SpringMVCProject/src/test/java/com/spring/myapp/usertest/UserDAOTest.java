package com.spring.myapp.usertest;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.repository.IUserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class UserDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOTest.class);
	
	@Autowired
	IUserDAO userdao;
	
	/*
	@Test
	public void InsertTest() throws Exception {
		UserVO vo = new UserVO();
		
		vo.setUserId("아이디1");
		vo.setUserPw("qlalfqjsgh1");
		vo.setUserName("이름1");
		
		userdao.register(vo);
		
		logger.info("아이디 : " + vo.getUserId() + "비밀번호 : " + vo.getUserPw() + "이름 : " + vo.getUserName());
	}
 */
	
	/*
	@Test
	public void duplicateTest() throws Exception{
		int n = userdao.isDuplicateId("jian");
		if(n == 1) {
			System.out.println("아이디 중복!!");
		}else {
			System.out.println("아이디 사용 가능!!");
		}
	}
	*/
	
	@Test
	public void loginTest() throws Exception{
		
		//로그인 처리를 위해 암호화된 db패스워드를 디코딩하여 비교
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		LoginVO login = new LoginVO();
		login.setUserId("chunjian");
		login.setUserPw("rkskek2@");
		
		UserVO user = userdao.login(login);
		System.out.println("로그인 시도 회원정보 : " + user);
		
		if(user != null) {
			String dbPw = user.getUserPw();
			System.out.println("db password: " + dbPw);
			String inputPw = login.getUserPw();
			System.out.println("Input password : " + inputPw);
			
			if(encoder.matches(inputPw, dbPw)) {
				System.out.println("로그인 성공!");
			}else {
				System.out.println("비밀번호가 틀렸어요 ㅠㅠ");
			}
		}else {
			System.out.println("존재하지 않는 회원입니다.");
		}
	}
	
	
	
	
	
}
