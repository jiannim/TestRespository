package com.spring.myapp.user.service;

import java.util.Date;
import java.util.Map;

import com.spring.myapp.user.model.LoginVO;
import com.spring.myapp.user.model.UserVO;

public interface IUserService {
	void register(UserVO user) throws Exception;
	
	UserVO login(LoginVO login) throws Exception;
	
	int isDuplicateId(String userId) throws Exception;
	
	void keepLogin(String userId, String sessionId, Date sessionLimit) throws Exception;
	
	UserVO getUserWithSessionId(String sessionId) throws Exception;
}
