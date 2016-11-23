package com.cmz.web1.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.cmz.web1.handler.SystemInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.cmz.web1.controller","com.cmz.web1.service"})
//@Order(2)
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	 
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		//aa
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	/**
	 * @ResponseBody 编码问题
	 * @param stringHttpMessageConverter
	 * @return
	 */
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter(StringHttpMessageConverter stringHttpMessageConverter){
		RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(stringHttpMessageConverter);
		adapter.setMessageConverters(converters);
		return adapter;
	}
	
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter(){
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
		supportedMediaTypes.add(MediaType.parseMediaType("text/plain;charset=UTF-8"));
		supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
		supportedMediaTypes.add(MediaType.parseMediaType("applicaiton/javascript;charset=UTF-8"));
		converter.setSupportedMediaTypes(supportedMediaTypes);
		
		return converter; 
	}
	
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping(HandlerInterceptor handlerInterceptor){
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		mapping.setInterceptors(handlerInterceptor);
		return mapping;
	}
	
	@Bean
	public HandlerInterceptor handlerInterceptor(){
		SystemInterceptor interceptor = new SystemInterceptor();
		
		return interceptor;
	}
	
	
	
	
	@Bean
	public FreeMarkerConfigurer freemarkerConfig(){
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/WEB-INF/views/");
		Properties settings = new Properties();
		settings.setProperty("default_encoding", "UTF-8");
		settings.setProperty("locale", "zh_CN");
		configurer.setFreemarkerSettings(settings);
		configurer.setDefaultEncoding("UTF-8");
		return configurer;
	}
	@Bean
	public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);

		//InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		//viewResolver.setPrefix("/WEB-INF/jsp/");
		//viewResolver.setSuffix(".jsp");
		UrlBasedViewResolver freeMarkerViewResolver = new UrlBasedViewResolver();
		//urlBasedViewResolver.setPrefix("/WEB-INF/views/");
		freeMarkerViewResolver.setSuffix(".html");
		//Map<String, String> attributes = new HashMap<String, String>();
		//attributes.put("contentType", "text/html; charset=utf-8");
		//freeMarkerViewResolver.setAttributesMap(attributes);
		//freeMarkerViewResolver.setViewNames("*.html");
		freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
		//freeMarkerViewResolver.setExposeRequestAttributes(true);
		freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
		
		MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
		defaultView.setExtractValueFromSingleKeyModel(true);
	
		ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		contentViewResolver.setViewResolvers(Arrays.<ViewResolver> asList(freeMarkerViewResolver));
		contentViewResolver.setDefaultViews(Arrays.<View> asList(defaultView));
		return contentViewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
