package com.cmz.web1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cmz.web1.crypto.MyPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	@Qualifier("mysql")
//	private DataSource dataSource;
	/**
	 * 参考 http://elim.iteye.com/blog/2157769
	 * @param auth
	 * @throws Exception
	 */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("marissa").password("koala").roles("USER").and().withUser("paul")
                .password("emu").roles("USER");
//        auth.jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery("select uname as username,pwd as password,enabled from user where uname = ?")
//        .authoritiesByUsernameQuery("select uname as username, 'USER' as authority from user where uname = ?")
//        .passwordEncoder(new MyPasswordEncoder());
        
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**","/webjars/**", "/assets/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
                 http
            .authorizeRequests()
                .antMatchers("/login.jsp").permitAll()
                .anyRequest().hasRole("USER")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/login.jsp?authorization_error=true")
                .and()
            // TODO: put CSRF protection back into this endpoint
            .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
            .logout()
            	.logoutUrl("/logout")
                .logoutSuccessUrl("/login.jsp")
                .and()
            .formLogin()
            	.loginProcessingUrl("/login")
                .failureUrl("/login.jsp?authentication_error=true")
                .loginPage("/login.jsp");
        // @formatter:on
    }
}
