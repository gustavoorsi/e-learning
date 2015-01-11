package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class RestApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	// adding xml return for rest services.
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.defaultContentType(MediaType.APPLICATION_JSON).favorPathExtension(true).mediaType("xml", MediaType.APPLICATION_XML);
//	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//		List<MediaType> mediaTypes = new ArrayList<MediaType>();
//		mediaTypes.add(MediaType.APPLICATION_XML);
//
//		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
//		xmlConverter.setSupportedMediaTypes(mediaTypes);
//
//		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
//		xmlConverter.setMarshaller(xStreamMarshaller);
//		xmlConverter.setUnmarshaller(xStreamMarshaller);
//
//		converters.add(xmlConverter);
//
//	}
}
