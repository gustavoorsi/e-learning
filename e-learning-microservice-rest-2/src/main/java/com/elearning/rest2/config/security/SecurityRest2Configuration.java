package com.elearning.rest2.config.security;

import static com.elearning.constants.ElearningConstants.API_SERVICE_2.AUTOCONFIG_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.BEANS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.CONFIGPROPS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.ENV_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.MAPPINGS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.METRICS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.SHUTDOWN_ENDPOINT;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.elearning.rest2.security.authenticators.external.ExternalServiceAuthenticator;
import com.elearning.rest2.security.authenticators.external.SomeExternalServiceAuthenticator;
import com.elearning.rest2.security.filters.AuthenticationFilter;
import com.elearning.rest2.security.filters.ManagementEndpointAuthenticationFilter;
import com.elearning.rest2.security.providers.BackendAdminUsernamePasswordAuthenticationProvider;
import com.elearning.rest2.security.providers.DomainUsernamePasswordAuthenticationProvider;
import com.elearning.rest2.security.providers.TokenAuthenticationProvider;
import com.elearning.rest2.security.services.TokenService;

//@formatter:off
/**
 * 
 * Here is some explanation about the annotations we are using:
 * 
 * 
 * 
 * @Configuration: tells Spring that this class will act as a configuration source. (There can be many such classes.)
 * 
 * @EnableWebMvcSecurity: A lot is happening by adding this one. Security filters with filter chain are configured and applied. @AuthenticationPrincipal
 *                        annotation starts working. ExceptionTranslationFilter catches AuthenticationExceptions and forwards to proper
 *                        AuthorizationEntryPoints. Basically, after this annotation alone our MVC services are not directly accessible
 *                        anymore.
 * 						  @EnableWebMvcSecurity VS @EnableWebSecurity:
 * 						  @EnableWebMvcSecurity annotation which will do the same as @EnableWebSecurity and provide integration with Spring MVC. Among other
 *                        things, it will ensure our CSRF Token is included in our forms automatically (when using Thymleaf 2.1 for example.)
 * 
 * @EnableGlobalMethodSecurity: Allows AOP @PreAuthorize and some other annotations to be applied to methods.
 * 
 * 
 * 
 * 
 * 
 * 
 * Also, SecurityRest2Configuration extends WebSecurityConfigurerAdapter which allows to fine tune some configuration
 * 
 * 
 * @author Gustavo Orsi
 *
 */
//@formatter:on
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityRest2Configuration extends WebSecurityConfigurerAdapter {

	@Value("${backend.admin.role}")
	private String backendAdminRole;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(). //
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS). //
				and(). //
				authorizeRequests(). //
				antMatchers(actuatorEndpoints()).hasRole(backendAdminRole). //
				anyRequest().authenticated(). //
				and(). //
				anonymous().disable(). //
				exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

		http //
		.addFilterBefore(//
				new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class)//
				.addFilterBefore( //
						new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}

	/**
	 * Get a list of all acutator endpoints we want to secure.
	 * 
	 * @return
	 */
	private String[] actuatorEndpoints() {
		return new String[] { AUTOCONFIG_ENDPOINT, BEANS_ENDPOINT, CONFIGPROPS_ENDPOINT, ENV_ENDPOINT, MAPPINGS_ENDPOINT, METRICS_ENDPOINT, SHUTDOWN_ENDPOINT };
	}

	/**
	 * For any AuthenticationException we want to return the 401 error. That’s all that we want for our REST clients to know. How they
	 * handle it is up them.
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint unauthorizedEntryPoint() {
		return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	}

	/**
	 * Override that allows configuring AuthenticationManager. Three AuthenticationProviders are added, each supporting different class of
	 * input Authentication object.
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(backendAdminUsernamePasswordAuthenticationProvider())
				.authenticationProvider(domainUsernamePasswordAuthenticationProvider()).authenticationProvider(tokenAuthenticationProvider());
	}

	@Bean
	public AuthenticationProvider backendAdminUsernamePasswordAuthenticationProvider() {
		return new BackendAdminUsernamePasswordAuthenticationProvider();
	}

	@Bean
	public AuthenticationProvider domainUsernamePasswordAuthenticationProvider() {
		return new DomainUsernamePasswordAuthenticationProvider(tokenService(), someExternalServiceAuthenticator());
	}

	@Bean
	public AuthenticationProvider tokenAuthenticationProvider() {
		return new TokenAuthenticationProvider(tokenService());
	}

	@Bean
	public TokenService tokenService() {
		return new TokenService();
	}

	@Bean
	public ExternalServiceAuthenticator someExternalServiceAuthenticator() {
		return new SomeExternalServiceAuthenticator();
	}

}
