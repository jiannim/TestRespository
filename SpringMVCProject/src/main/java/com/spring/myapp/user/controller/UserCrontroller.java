package com.spring.myapp.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;
import com.spring.myapp.user.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserCrontroller {

	@Autowired
	private IUserService service;

	private static final Logger logger = LoggerFactory.getLogger(UserCrontroller.class);
	//회원가입 처리요청
	@PostMapping("")
	public String register(@RequestBody UserVO user) throws Exception {

		System.out.println("Param: "+user);
		service.register(user);
		return "joinSuccess";
	}


	//회원가입 페이지
	@RequestMapping(value="/join")
	public ModelAndView register() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/join");
		return mv;
	}


	//아이디 중복체크 요청
	@PostMapping("/idCheck")
	public Map<String, Object> confirmId(@RequestBody String userId) throws Exception{
		System.out.println("아이디 중복 요청 : " + userId);
		Map<String, Object> data = new HashMap<>();

		int result = service.isDuplicateId(userId);
		if(result == 0) {
			System.out.println("아이디 사용 가능!!!");
			data.put("confirm", "OK");
		}else {
			System.out.println("아이디 중복됬어요 ㅠㅠ");
			data.put("confirm", "NO");
		}

		return data;
	}


	//로그인 페이지 요청
	@GetMapping("/login")
	public ModelAndView login() throws Exception{
		//			logger.info("/user/login 요청!! : GET");
		return new ModelAndView("user/login");
	}

	//로그인 검증 요청
	@PostMapping("/loginCheck")
	public String login(@RequestBody LoginVO login, HttpSession Session, HttpServletResponse res) throws Exception{


		//1.세션을 얻는 방법 HttpServletRequest객체 사용
		//request.getSession();
		//2. HttpSession객체 사용

		/*
		 * 클라이언트가 전송한 id값과 pw값을 가지고 db에서 회원의 정보를 조회해서 불러온다음 값비교를 통해 아이다가 없을경우 클라이언트측으로 문자열 "idFail"전송
		 * 비밀번호가 틀렸을 경우 문자열 "pwFail"정송
		 * 로그인 성공시 문자열 "loginSuccess" 전송
		 */

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserVO user = service.login(login);
		logger.info("로그인 시도 회원정보: " + user);

		String result = null;

		if(user != null) {
			//아이디 존재
			if(encoder.matches(login.getUserPw(), user.getUserPw())) {
				//비밀번호 일치 -> 로그인 성공
				result = "loginSuccess";
				Session.setAttribute("login", user);
				//Session.setMaxInactiveInterval(60 * 60); //세션 만료 시간을 1시간으로 증가

				//자동 로그인 쿠키 생성
				if(login.isAutoLogin()) {
					logger.info("자동로그인 체크함!!");

					long limitTime = 60 *60 * 24 * 90;
					Date sessionLimit = new Date(System.currentTimeMillis() + (limitTime * 1000));
					
					
					//쿠키 생성
					Cookie loginCookie =  new Cookie("loginCookie", Session.getId());
					//쿠키 속성 설정
					loginCookie.setMaxAge((int)limitTime);
					loginCookie.setPath("/");
					//생성된 쿠키를 response객체에 실어서 전송.
					res.addCookie(loginCookie);
					
					service.keepLogin(user.getUserId(), Session.getId(), sessionLimit);
				}
			}else {
					//비밀번호 틀림
					result = "pwFail";
				}
			} else {
				//아이디 없음
				result = "idFail";
			}

			return result;
		}
	
	//로그아웃 요청
	   @GetMapping("/logout")
	   public ModelAndView logout(HttpServletRequest request,
	            HttpServletResponse response,
	            HttpSession session) throws Exception {
	      
	      Object object = session.getAttribute("login");
	        if (object != null) {
	            UserVO userVO = (UserVO) object;
	            session.removeAttribute("login");
	            session.invalidate();
	            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
	            if (loginCookie != null) {
	                loginCookie.setPath("/");
	                loginCookie.setMaxAge(0);
	                response.addCookie(loginCookie);
	                service.keepLogin(userVO.getUserId(), "none", new Date());
	            }
	        }
	      
	      return new ModelAndView("redirect:/");
	   }
	}

