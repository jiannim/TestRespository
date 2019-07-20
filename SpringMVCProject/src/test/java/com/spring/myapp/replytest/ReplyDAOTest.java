package com.spring.myapp.replytest;

import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.myapp.boardtest.BoardDAOTest;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;
import com.spring.myapp.reply.repository.IReplyDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:/spring/mvc-config.xml"})
public class ReplyDAOTest {

	@Autowired
	private IReplyDAO dao;
	
	/*
	@Test
	public void testReplyInsert() throws Exception {
		
		for(int i = 1; i<= 300; i++) {
		ReplyVO reply = new ReplyVO();
		reply.setBoardNo(2997);
		reply.setReplyText("댓글이다 캬캬"+i);
		reply.setReplyWriter("왈왈이"+i);
		
		dao.insert(reply);
		}
		System.out.println("댓글 등록 성공!!!!");
	}
	*/
	
	/*
	@Test
	public void replyListTest() throws Exception {
		for (ReplyVO vo : dao.list(2997)) {
			if(vo.getReplyNo() == 251) break;
			System.out.println(vo);
		}
	}
	*/
	
	/*
	@Test
	public void replyupdateTest() throws Exception {
		ReplyVO reply = new ReplyVO();
		reply.setBoardNo(2997);
		reply.setReplyNo(99);
		reply.setReplyText("수정했다 하하하하");
		dao.update(reply);
		System.out.println("수정완료");
	}
	*/
	
	/*
	@Test
	public void replydeleteTest() throws Exception{
		ReplyVO reply = new ReplyVO();
		reply.setBoardNo(2997);
		reply.setReplyNo(60);
		
		dao.delete(reply.getReplyNo());
		System.out.println("삭제 완료!!");
	}
	*/
	
	//댓글 페이징 테스트
	@Test
	public void listPaginTest() throws Exception {
		Criteria cri = new Criteria();
		
		cri.setPage(2);
		cri.setCountPerPage(20);
		
		for(ReplyVO reply : dao.listPaging(cri, 2997)) {
			System.out.println(reply);
		}
	}
	
	
	
	
	
	
	
	
}
