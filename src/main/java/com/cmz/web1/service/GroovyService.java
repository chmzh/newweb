package com.cmz.web1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmz.web1.scripting.Messenger;

@Service
public class GroovyService {
	@Autowired
	private Messenger messenger;
	
	public String getMessage(){
		return messenger.getMessage();
	}
}
