package com.spring.myapp.board.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;


//자동 빈 등록 아노테이션 : @Component, @Controller, @Service, @Repository
@Repository
public class BoardDAO implements IBoardDAO {
	
	private final SqlSession sqlSession;
	
	private static final String NAMESPACE = "BoardMapper";
	
	//의존성 자동주입 아노테이션: @Autowired, @Inject, @Resources
	@Autowired
	public BoardDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insert(BoardVO article) throws Exception {
		sqlSession.insert(NAMESPACE+".insert", article);
	}

	@Override
	public BoardVO getArticle(int boardNo) throws Exception {
		System.out.println("게시글 번호: " + boardNo);
		return sqlSession.selectOne(NAMESPACE+".getArticle", boardNo);
	}

	@Override
	public void update(BoardVO article) throws Exception {
		sqlSession.update(NAMESPACE+".update", article);
	}

	@Override
	public void delete(int boardNo) throws Exception {
		sqlSession.delete(NAMESPACE+".delete", boardNo);
	}

	@Override
	public List<BoardVO> getAllArticles() throws Exception {
		return sqlSession.selectList(NAMESPACE+".getAllArticles");
	}

	@Override
	public List<BoardVO> listPaging(Criteria cri) throws Exception {
		
		//page변수에 1이 저장되어있다면? limit 절의 시작값은 0
		//"" 2가 저장되어있다면? limit 절의 시작값은 10
		//페이지 재계산 : (페이지 번호 - 1) * 한페이지당 들어갈 게시물 수
		//page = (page - 1) *10;

		//return sqlSession.selectList(NAMESPACE+".listPaging", page);
		return sqlSession.selectList("BoardMapper.listPaging", cri);
	}

	@Override
	public int countArticles() throws Exception {
		return sqlSession.selectOne("BoardMapper.countArticles");
	}
	
	
	// 어떤걸 고르든 알아서 골라 처리해줄게!
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return sqlSession.selectList("BoardMapper.listSearch", cri);
	}

	//어떤걸 고르든 알아서 갯수 구해줄게!
	@Override
	public int countSearchArticles(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne("BoardMapper.countSearchArticles", cri);
	}
	
	
	
	

	@Override
	public List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception {
		return sqlSession.selectList("BoardMapper.listSearchByTitle", cri);
	}

	@Override
	public void updateViewCnt(int boardNo) throws Exception {
		sqlSession.update("BoardMapper.updateViewCnt", boardNo);
	}


	@Override
	public void updatereplyCnt(int boardNo, int count) throws Exception {
		Map<String, Object> datas = new HashMap<>();
		datas.put("boardNo", boardNo);
		datas.put("count", count);
		sqlSession.update("BoardMapper.updatereplyCnt", datas);
	}
}






