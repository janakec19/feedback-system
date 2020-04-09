package com.fms.events.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.fms.events.service.security.CustomUserInfoTokenServices;
import com.fms.events.service.security.OAuth2FeignRequestInterceptor;

import feign.RequestInterceptor;

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
	@Autowired
    private ResourceServerProperties sso;
    
    @Autowired
    private OAuth2ClientContext context;

    @Bean
    public ResourceServerTokenServices tokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }
    
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return new OAuth2FeignRequestInterceptor(context);
    }

}
