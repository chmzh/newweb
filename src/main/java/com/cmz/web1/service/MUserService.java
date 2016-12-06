package com.cmz.web1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmz.web1.mongodb.dao.MUserDao;
import com.mongodb.DBCollection;

@Service
public class MUserService {
	@Autowired
	private MUserDao userDao;
	
	public String getUser(){
		return userDao.getUser();
	}
}
