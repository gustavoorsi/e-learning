package com.elearning.authserver.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class OauthTableConfiguration extends AuthorizationServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(OauthTableConfiguration.class, args);
	}

	private static final String CREATE_OAUTH_ACCESS_TOKEN_SQL = "create table if not exists oauth_access_token (" + //
			"token_id VARCHAR(256)," + //
			"token LONGVARBINARY," + //
			"authentication_id VARCHAR(256)," + //
			"user_name VARCHAR(256)," + //
			"client_id VARCHAR(256)," + //
			"authentication LONGVARBINARY," + //
			"refresh_token VARCHAR(256)" + //
			");";

	private static final String DELETE_TOKENS_SQL = "delete from oauth_access_token";

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void setUpTokenDatasource() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute(CREATE_OAUTH_ACCESS_TOKEN_SQL);
		jdbcTemplate.execute(DELETE_TOKENS_SQL);
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() //
				.withClient("curl") //
				.authorities("ROLE_ADMIN") //
				.resourceIds("jaxenter") //
				.scopes("read", "write") //
				.authorizedGrantTypes("client_credentials") //
				.secret("password") //
				.and() //
				.withClient("web") //
				.redirectUris("http://github.com/techdev-solutions/") //
				.resourceIds("jaxenter") //
				.scopes("read") //
				.authorizedGrantTypes("implicit");
	}

}
