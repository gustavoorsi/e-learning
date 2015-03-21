package com.elearning.web.service.endpointdiscovery;

import org.springframework.stereotype.Component;

import com.elearning.constants.ElearningConstants;

@Component(value = "basicRestServiceDiscovery")
public class BasicRestServiceDiscovery implements ServiceDiscovery {

	private static final String REST_1_REST_URL = "http://localhost:8081" + ElearningConstants.API_SERVICE_1.API_PATH;

	@Override
	public String getServiceUrl() {
		return REST_1_REST_URL;
	}

}
