package com.cmz.web1.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmz.web1.dao.UserDao;

@Service
public class UserService {
	
	@PostConstruct
	public void init(){
		System.out.println(UserService.class.getName()+"init");
	}
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public void update(){
		int r = userDao.update("abcaa", 9);
		int c = userDao.count("user");
	}
}
