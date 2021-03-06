package com.spring.myapp.reply.repository;

import java.util.List;

import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.reply.model.ReplyVO;

public interface IReplyDAO {

	//댓글 목록 불러오기
	List<ReplyVO> list(int boardNo) throws Exception;
	
	//댓글 쓰기 기능
	void insert(ReplyVO reply) throws Exception;
	
	//댓글 수정 기능
	void update(ReplyVO reply) throws Exception;
	
	//듯글 삭제 기능
	void delete(int replyNo) throws Exception;
	
	//페이징 처리된 게시글 목록 불러오기
	List<ReplyVO> listPaging(Criteria cri, int boardNo) throws Exception;
	
	//특정 게시물의 총 댓글 수 불러오기 기능
	int countReplies(int boardNo) throws Exception;
	
	void deleteAll(int boardNo) throws Exception;
}
