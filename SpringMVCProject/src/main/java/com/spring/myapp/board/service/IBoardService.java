package com.spring.myapp.board.service;

import java.util.List;

import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.SearchCriteria;

public interface IBoardService {
	void insert(BoardVO article) throws Exception;
	BoardVO getArticle(int boardNo, boolean trigger) throws Exception;
	void update(BoardVO article) throws Exception;
    void delete(int boardNo) throws Exception;
    
    List<BoardVO> getAllArticles() throws Exception;
    
    List<BoardVO> listPaging(Criteria cri) throws Exception;
    
    int countArticles() throws Exception;
    
    List<BoardVO> listSearchByTitle(SearchCriteria cri) throws Exception;
    //int countSearchedArticles(SearchCriteria cri) throws Exception;
    //List<BoardVO> searchwriter(SearchCriteria cri) throws Exception;
    
    
    
    
    List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
    int countSearchArticles(SearchCriteria cri) throws Exception;
}
