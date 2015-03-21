package com.elearning.web.service.impl.remote;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elearning.model.entities.Foo;
import com.elearning.service.FooService;
import com.elearning.web.service.endpointdiscovery.ServiceDiscovery;

@Service(value = "fooRestService")
public class FooServiceRestImpl implements FooService {

	@Autowired
	@Qualifier(value = "oauth2RestTemplateClientCredentials")
	private RestTemplate restTemplate;

	@Autowired
	@Qualifier(value = "basicRestServiceDiscovery")
	private ServiceDiscovery serviceDiscovery;

	@Override
	public Page<Foo> findAll(Pageable pageable) {

		List<Foo> foos = new ArrayList<Foo>();

		String host = serviceDiscovery.getServiceUrl();

		String endpoint = host + "/foos";

		ResponseEntity<PagedResources<Resource<Foo>>> pagedResourceResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<Resource<Foo>>>() {
				});

		PagedResources<Resource<Foo>> fooPage = pagedResourceResponse.getBody();

		for (Resource<Foo> pageItem : fooPage.getContent()) {
			Foo foo = pageItem.getContent();

			foos.add(foo);
		}

		return new PageImpl<Foo>(foos);
	}

	@Override
	public Foo findById(Long fooId) {
		// TODO Auto-generated method stub
		return null;
	}

}
