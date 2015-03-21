package com.elearning.rest2.security.filters;

import static com.elearning.constants.ElearningConstants.API_SERVICE_2.AUTOCONFIG_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.BEANS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.CONFIGPROPS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.ENV_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.MAPPINGS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.METRICS_ENDPOINT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_2.SHUTDOWN_ENDPOINT;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.elearning.rest2.security.authentications.BackendAdminUsernamePasswordAuthenticationToken;
import com.google.common.base.Optional;

/**
 * 
 * Authenticate with a backend admin user if the logged in user has access to management endpoints, such as those exposed by .actuator'.
 * 
 * These urls are secured in SecurityRest2Configuration, by overriding configure(HttpSecurity http) from WebSecurityConfigurerAdapter.
 * 
 * Look at line: antMatchers(actuatorEndpoints()).hasRole(backendAdminRole).
 * 
 * 
 * ManagementEndpointAuthenticationFilter is very similar to AuthenticationFilter but it does not make use of tokens. It has hardcoded
 * backend admin login username with password provided from property at deployment time. It checks if username and password from headers
 * matches these when targeting Spring-Actuator endpoints that require authentication. For example, /health is unprotected while /metrics
 * is.
 * 
 * 
 * @author Gustavo Orsi
 *
 */
public class ManagementEndpointAuthenticationFilter extends GenericFilterBean {

	private final static Logger logger = LoggerFactory.getLogger(ManagementEndpointAuthenticationFilter.class);
	private AuthenticationManager authenticationManager;
	private Set<String> managementEndpoints;

	public ManagementEndpointAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		prepareManagementEndpointsSet();
	}

	private void prepareManagementEndpointsSet() {
		managementEndpoints = new HashSet<>();
		managementEndpoints.add(AUTOCONFIG_ENDPOINT);
		managementEndpoints.add(BEANS_ENDPOINT);
		managementEndpoints.add(CONFIGPROPS_ENDPOINT);
		managementEndpoints.add(ENV_ENDPOINT);
		managementEndpoints.add(MAPPINGS_ENDPOINT);
		managementEndpoints.add(METRICS_ENDPOINT);
		managementEndpoints.add(SHUTDOWN_ENDPOINT);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = asHttp(request);
		HttpServletResponse httpResponse = asHttp(response);

		Optional<String> username = Optional.fromNullable(httpRequest.getHeader("X-Auth-Username"));
		Optional<String> password = Optional.fromNullable(httpRequest.getHeader("X-Auth-Password"));

		String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);

		try {
			if (postToManagementEndpoints(resourcePath)) {
				logger.debug("Trying to authenticate user {} for management endpoint by X-Auth-Username method", username);
				processManagementEndpointUsernamePasswordAuthentication(username, password);
			}

			logger.debug("ManagementEndpointAuthenticationFilter is passing request down the filter chain");
			chain.doFilter(request, response);
		} catch (AuthenticationException authenticationException) {
			SecurityContextHolder.clearContext();
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
		}
	}

	private HttpServletRequest asHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	private HttpServletResponse asHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	private boolean postToManagementEndpoints(String resourcePath) {
		return managementEndpoints.contains(resourcePath);
	}

	private void processManagementEndpointUsernamePasswordAuthentication(Optional<String> username, Optional<String> password) throws IOException {
		Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(username, password);
		SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
	}

	private Authentication tryToAuthenticateWithUsernameAndPassword(Optional<String> username, Optional<String> password) {
		BackendAdminUsernamePasswordAuthenticationToken requestAuthentication = new BackendAdminUsernamePasswordAuthenticationToken(username, password);
		return tryToAuthenticate(requestAuthentication);
	}

	private Authentication tryToAuthenticate(Authentication requestAuthentication) {
		Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			throw new InternalAuthenticationServiceException("Unable to authenticate Backend Admin for provided credentials");
		}
		logger.debug("Backend Admin successfully authenticated");
		return responseAuthentication;
	}
}
