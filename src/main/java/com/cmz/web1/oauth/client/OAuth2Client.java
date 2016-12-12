package com.cmz.web1.oauth.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

public class OAuth2Client {
	public static void main(String[] args) {
		ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();		
//		List<String> scopes = new ArrayList<String>(2);
//        scopes.add("write");
//        scopes.add("read");
        resource.setAccessTokenUri("http://localhost:8080/newweb/oauth/token");
        resource.setClientId("my-trusted-client");
        //resource.setClientSecret("restapp");
        resource.setGrantType("password");
        //resource.setScope(scopes);

        resource.setUsername("marissa");
        resource.setPassword("koala");
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
		OAuth2AccessToken token = template.getAccessToken();
		System.out.println(token);
		
		
		
	}
}
