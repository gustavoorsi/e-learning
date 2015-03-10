package com.elearning.rest2.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

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

}
