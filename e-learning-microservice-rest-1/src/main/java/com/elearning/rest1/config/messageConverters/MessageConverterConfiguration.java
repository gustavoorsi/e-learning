package com.elearning.rest1.config.messageConverters;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * Configuration class only to set a pretty print for json converter. Note this is a demo project so it's ok to set it. For production use,
 * do not set this property never.
 * 
 * 
 * @author Gustavo Orsi
 *
 */
@Configuration
public class MessageConverterConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(jackson2Converter());
	}

	/**
	 * just to set the pretty-print feature on.
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter jackson2Converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(myObjectMapper());
		return converter;
	}

	@Bean
	public ObjectMapper myObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // set to pretty-print. Use it for dev purpose.
		return objectMapper;
	}

}
