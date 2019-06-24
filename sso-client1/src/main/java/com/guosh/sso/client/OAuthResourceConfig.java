package com.guosh.sso.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@Order(2)
public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId("api");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
    	http
    		.requestMatchers().antMatchers("/api/**")
    		.and()
    		.authorizeRequests()
    		// 获取当前用户的基本信息
    		//.antMatchers("/api/**").hasAuthority("USER");
		   .antMatchers("/api/**").hasAnyAuthority("ADMIN","TEACHER","STUDENT","CLASSTEACHER","ACTIVITYADMIN");
	}
}
