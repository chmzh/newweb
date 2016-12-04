package com.cmz.web1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("/WEB-INF/dispatcher-servlet.xml")  //加这个表单验证用jsr303 annotation才有效 @Valid 例如ThymeleafController中的例子
public class AppConfig {

}
