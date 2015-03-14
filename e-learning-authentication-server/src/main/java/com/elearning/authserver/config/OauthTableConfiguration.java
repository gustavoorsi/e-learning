package com.elearning.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 
 * OAuth usually has an authorization server and resource servers. My rest 1 API is the resource server. Now, I could add the authentication
 * server inside the same application but I don’t like that approach. I’m going to use different applications for that. The only way these
 * two applications need to communicate is through a shared database for the authorization tokens for which I will use SQLite.
 * 
 * The configuration is pretty straight forward: A database for the tokens and some example clients that I define in memory. As this is not
 * an article on OAuth I will not explain what things like authorizedGrantTypes mean.
 * 
 * 
 * http://jaxenter.com/rest-api-spring-java-8-112289.html
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
@EnableAuthorizationServer
public class OauthTableConfiguration extends AuthorizationServerConfigurerAdapter {

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

	// @Autowired
	// private DataSource dataSource;
	//
	// @PostConstruct
	// public void setUpTokenDatasource() {
	// JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	// jdbcTemplate.execute(CREATE_OAUTH_ACCESS_TOKEN_SQL);
	// jdbcTemplate.execute(DELETE_TOKENS_SQL);
	// }

	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
		// return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
	}

	/**
	 * This configuration is used to hand out tokens to access resource servers. For example, the client with the client_credentials grant
	 * can get a token directly from the /oauth/token endpoint. The client with the implicit grant sends a user to the /oauth/authorize page
	 * where the user can authorize the client to access the data on the resource server. The cool thing is, all these endpoints and needed
	 * web pages are in a default version included in Spring Security OAuth!
	 */
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
