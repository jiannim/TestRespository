package com.spring.myapp.board.repository;

import java.util.List;
import java.util.Map;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

public interface IBoardDAO {

	void insert(BoardVO article) throws Exception;
	BoardVO getArticle(int boardNo) throws Exception;
	void update(BoardVO article) throws Exception;
    void delete(int boardNo) throws Exception;
    
    //게시글 전체목록 불러오기
    List<BoardVO> getAllArticles() throws Exception;
    
    //게시글 페이지별로 불러오기
    List<BoardVO> listPaging(Criteria cri) throws Exception;
    
    //총 게시물 수를 불러오기
    int countArticles() throws Exception;
    
    //제목으로 검색  sql처리
    List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception;
    
    //검색 후 총 게시물수를 가져오기
    //int countSearchedArticles(SearchCriteria cri) throws Exception;
    //List<BoardVO> searchwriter(SearchCriteria cri) throws Exception;
    
    
    //조회수 상승 처리
    void updateViewCnt(int boardNo) throws Exception;
    
    //댓글 갯수 상승,하락 처리
    void updatereplyCnt(int boardNo, int count) throws Exception;
    
    
    //검색 동적 sql 처리(어떤걸 고르는 알아서 처리해줄게! 세트로 안만들고도 가능해!)
    List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
    int countSearchArticles(SearchCriteria cri) throws Exception;
}







