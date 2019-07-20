package com.spring.myapp.board.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.myapp.board.model.BoardVO;
import com.spring.myapp.board.service.IBoardService;
import com.spring.myapp.commons.paging.Criteria;
import com.spring.myapp.commons.paging.PageCreator;
import com.spring.myapp.commons.paging.SearchCriteria;
// 주석을 추가했어요~~
@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	private IBoardService service;


	//게시글 목록요청 처리 메서드
	/*

	// 페이징 처리 전 
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception{

		logger.info("/board/list request~~~~");
		List<BoardVO> articles =  service.getAllArticles();

		System.out.println("=================");
		for(BoardVO vo : articles)
			System.out.println(vo);
		System.out.println("=================");

		model.addAttribute("articles", articles);
		return "board/list";
	}
	 */
/*
	//페이징 처리 후 게시글 목록 요청
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Criteria cri, Model model) throws Exception {

		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countArticles());

		model.addAttribute("pageCreator", pc);


		logger.info("/board/list : GET요청 발생!!");

		model.addAttribute("articles", service.listPaging(cri));

		return "board/list";
	}
	 */

	//검색 처리 후 게시글 목록 불러오기
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(SearchCriteria cri, Model model) throws Exception {

		logger.info("/board/list : GET요청 발생!!");

		PageCreator pc = new PageCreator();
		pc.setCriteria(cri);
		pc.setArticleTotalCount(service.countSearchArticles(cri));
		model.addAttribute("articles", service.listSearch(cri));
		model.addAttribute("pageCreator", pc);
		model.addAttribute("condition", cri.getCondition());
		model.addAttribute("keyword", cri.getKeyword());
		return "board/list";
	}






	//게시글 등록 폼
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model, HttpSession Session) {
		
		return "board/write";
	}
	//게시글 등록 요청
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVO vo,Model model, RedirectAttributes redAttr) throws Exception {
		logger.info(vo.toString());
		service.insert(vo);
		redAttr.addFlashAttribute("message", "regSuccess");

		return "redirect:/board/list";
	}


	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public String content(@RequestParam("boardNo") int boardNo,@RequestParam("page") int page,
			@RequestParam("countPerPage") int count, SearchCriteria cri,
			Model model) throws Exception {
		model.addAttribute("article", service.getArticle(boardNo, true));

		System.out.println("/content 요청시 받은 목록 수: " + count);

		model.addAttribute("criteria", cri);
		model.addAttribute("page", page);
		model.addAttribute("countPerPage", count);
		return "board/content";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam("boardNo") int boardNo, @RequestParam("page") int page, 
			@RequestParam("countPerPage") int countPerPage, Model model) throws Exception {
		
		model.addAttribute("modify", service.getArticle(boardNo, false));
		model.addAttribute("page", page);
		model.addAttribute("countPerPage", countPerPage);
		return "board/modify";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String content(SearchCriteria crie, BoardVO article, Criteria cri, RedirectAttributes redirectAttributes , Model model) throws Exception {
		service.update(article);
		model.addAttribute("crie", crie);
		redirectAttributes.addAttribute("page", cri.getPage());
		redirectAttributes.addAttribute("countPerPage", cri.getCountPerPage());
		redirectAttributes.addAttribute("message", "modSuccess");

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("boardNo") int boardNo, Criteria cri,
			RedirectAttributes redirectAttributes) throws Exception {
		service.delete(boardNo);
		redirectAttributes.addAttribute("page", cri.getPage());
		redirectAttributes.addAttribute("countPerPage", cri.getCountPerPage());
		redirectAttributes.addFlashAttribute("message", "delSuccess");

		return "redirect:/board/list";
	}
	
	
	


















}
