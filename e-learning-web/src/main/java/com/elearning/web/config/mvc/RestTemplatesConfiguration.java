package com.elearning.web.config.mvc;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
public class RestTemplatesConfiguration {

	@Bean(name = "oauth2RestTemplateClientCredentials")
	public OAuth2RestTemplate getOauth2RestTemplateClientCredentials(MappingJackson2HttpMessageConverter jackson2Converter) {

		ClientCredentialsResourceDetails clientDetails = new ClientCredentialsResourceDetails();
		clientDetails.setAccessTokenUri("http://localhost:8081/oauth/token");
		clientDetails.setClientId("my-client-with-secret");
		clientDetails.setClientSecret("secret");
		clientDetails.setScope(Arrays.asList("read"));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(clientDetails, clientContext);

		restTemplate.setMessageConverters(Arrays.asList(jackson2Converter));

		return restTemplate;

	}

}
