package com.spring.myapp.board.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.repository.IBoardDAO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;
import com.spring.myapp.reply.repository.IReplyDAO;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardDAO boardDao;
	@Autowired
	private IReplyDAO replyDao;
	
	
	
	@Override
	public void insert(BoardVO article) throws Exception {
		boardDao.insert(article);
	}

	@Transactional
	@Override
	public BoardVO getArticle(int boardNo, boolean trigger) throws Exception {
		BoardVO article = boardDao.getArticle(boardNo); 
		
		if(trigger) {
			String content =  article.getContent().replace("\n", "<br>")
					  .replace("\u0020", "&nbsp;");
			article.setContent(content);	
		}
		
		boardDao.updateViewCnt(boardNo);
		return article;
		
	}

	@Override
	public void update(BoardVO article) throws Exception {
		boardDao.update(article);
	}

	@Override
	public void delete(int boardNo) throws Exception {
		replyDao.deleteAll(boardNo);
		boardDao.delete(boardNo);
	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		return boardDao.getAllArticles();
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		return boardDao.listPaging(cri);
	}

	@Override
	public int countArticles() throws Exception {
		return boardDao.countArticles();
	}

	@Override
	public List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception {
		return boardDao.listSearchByTitle(cri);
	}

	
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		
		List<BoardVO> list = boardDao.listSearch(cri);
		//1일 이내에 쓰여진 게시물에만  new를 붙이는 처리
		for (BoardVO boardVO : list) {
			//게시물 목록을 요청한 시간 - 게시물 등록 시간 < 1일 미만이면  newMark를 true로 만들라.
		 	long now =  System.currentTimeMillis(); //현재시간 읽기 (타입은 롱으로 담아줘라)
			//Date date =  boardVO.getRegDate(); //게시물 등록 시간
			long regDate = boardVO.getRegDate().getTime();
			
			long ondDayMillis = 60 * 60 *24 *1000; //밀리초라 마지막에 곱하기 1000해줌(하루를 밀리초로나타낸거임)
			if(now - regDate <= ondDayMillis) {
				boardVO.setNewMark(true);
			}
		}
		return list;
	}

	@Override
	public int countSearchArticles(SearchCriteria cri) throws Exception {
		return boardDao.countSearchArticles(cri);
	}

	

	
}
