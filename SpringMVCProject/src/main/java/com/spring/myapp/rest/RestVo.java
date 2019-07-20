package com.spring.myapp.rest;

import java.util.*;

import lombok.Data;

@Data
public class RestVo {
	
	private int number;
	private String name;
	private List<String> hobbys;
	private Map<String, String> skills;
}
