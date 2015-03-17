package com.elearning.web.service.endpointdiscovery;

import org.springframework.stereotype.Component;

@Component(value = "basicRestServiceDiscovery")
public class BasicRestServiceDiscovery implements ServiceDiscovery {
	
	private static final String REST_1_REST_URL = "http://localhost:8081/api/v1/microservice1Endpoint";

	@Override
	public String getServiceUrl() {
		return REST_1_REST_URL;
	}

}
