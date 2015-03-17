package com.elearning.web.service.endpointdiscovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

@Component(value = "eurekaServiceDiscovery")
public class EurekaServiceDiscovery implements ServiceDiscovery {

	private static final String REST_1_SERVICE_NAME = "E-LEARNING-MICROSERVICE-REST-1";

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public String getServiceUrl() {

		InstanceInfo instanceInfo = this.discoveryClient.getNextServerFromEureka(REST_1_SERVICE_NAME, false);

		String hostname = instanceInfo.getHomePageUrl();

		return hostname;
	}

}
