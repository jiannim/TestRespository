package com.spring.myapp.user.repository;

import java.util.Map;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserDAO {
	
	//회원가입 처리
	void register(UserVO user) throws Exception;
	
	//아이디 중복확인
	int isDuplicateId(String userId) throws Exception;
	
	//로그인 시도 회원정보 조회
	UserVO login(LoginVO login) throws Exception;
	
	//자동로그이 유지 처리
	void keepLogin(Map<String, Object> datas) throws Exception;
	
	//세션아이디 검증 후 회원정보 불러오는 처리
	UserVO getUserWithSessionId(String sessionId) throws Exception;
	
}
