package com.cmz.web1.context;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;

import org.springframework.web.util.WebUtils;


public class ContextUtil {
	private static ServletContext servletContext;
	public static void setServletContext(ServletContext servletContext){
		ContextUtil.servletContext = servletContext;
	}
	
	public static String getPath(String path) throws FileNotFoundException{
		return WebUtils.getRealPath(servletContext, path);
	}
}
