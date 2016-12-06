package com.cmz.web1.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.FilterRegistration.Dynamic;

import org.springframework.mobile.device.DeviceResolverRequestFilter;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;
import org.springframework.web.util.WebAppRootListener;

import com.cmz.web1.context.ContextUtil;

public class ServletInitializer extends AbstractDispatcherServletInitializer {

	
	@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.scan(ClassUtils.getPackageName(getClass()));
		return context;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
	
	@Override
	protected Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
		// TODO Auto-generated method stub
		
		return super.registerServletFilter(servletContext, filter);
	}
	
//	@Override
//	protected Filter[] getServletFilters() {
//		Filter[] filters = new Filter[1];
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		characterEncodingFilter.setForceEncoding(true);
//		filters[0] = characterEncodingFilter;
//		
//		return filters;
//	}
	
	
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletContext.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false, "/*");
		//FilterRegistration.Dynamic filterDynamic = servletContext.addFilter("deviceResolverRequestFilter", new DeviceResolverRequestFilter());
        //EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
        //filterDynamic.addMappingForUrlPatterns(dispatcherTypes, true, "/*");
		
        servletContext.setInitParameter("webAppRootKey", "myweb.root");
        servletContext.addListener(WebAppRootListener.class);
        servletContext.setInitParameter("log4jConfigLocation", "/WEB-INF/conf/log4j.properties");
        //servletContext.setInitParameter("log4jRefreshInterval", "2000"); //2秒检查一次日志等级的改变
        servletContext.addListener(Log4jConfigListener.class);
        
        ContextUtil.setServletContext(servletContext);
        
		//servletContext.addServlet("dispatcher", DispatcherServlet.class).addMapping("/*");
		
		
		//		DelegatingFilterProxy filter = new DelegatingFilterProxy("springSecurityFilterChain");
//		filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
//		servletContext.addFilter("springSecurityFilterChain", filter).addMappingForUrlPatterns(null, false, "/*");
	}

}
