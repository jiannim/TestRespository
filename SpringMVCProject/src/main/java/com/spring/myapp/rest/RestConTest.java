package com.spring.myapp.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.PageCreator;


@RestController
@RequestMapping("/rest/test")
public class RestConTest {
	
	
	@RequestMapping("/hello")
	@ResponseBody
	public String syHelloWorld() {
		System.out.println("/rest/test/hello 요청이 들어옴!");
		return "Hello World!!ggggg";
	}

	@RequestMapping("/sendvo")
	@ResponseBody
	public RestVo sendVO() {
		
		RestVo vo = new RestVo();
		
		vo.setNumber(13);
		vo.setName("김까까");
		
		vo.setHobbys(Arrays.asList("운동","음악감상","독서"));
		
		Map<String, String> skiils = new HashMap<>();
		skiils.put("언어", "자바");
		skiils.put("운영체제", "위도우10");
		skiils.put("DBMS", "MySQL");
		skiils.put("프레임워크", "스프링");
		vo.setSkills(skiils);
		return vo;
	}
	
	@RequestMapping("/page-cre")
	@ResponseBody
	public PageCreator pageCreator() {
		
		PageCreator pc = new PageCreator();
		pc.setCriteria(new Criteria());
		
		return pc;
	}
	
	/////////////////////////////////////////////////////////
	//RestController에서 jsp파일 열기
	//리턴 타입을 ModelAnView타입으로 설정
	@RequestMapping("/setview")
	public ModelAndView test() {
		
		//ModelAndView mv = new ModelAndView();
		//mv.setViewName("test/setview");
		//mv.addObject("name", "홍길동");
	
		return new ModelAndView("test/setview", "name", "홍길동");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
