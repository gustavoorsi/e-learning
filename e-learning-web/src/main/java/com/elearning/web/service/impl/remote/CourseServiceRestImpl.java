package com.elearning.web.service.impl.remote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Course;
import com.elearning.model.exception.CourseTopicAlreadyExistException;
import com.elearning.service.CourseService;
import com.elearning.web.service.endpointdiscovery.ServiceDiscovery;

@Service(value = "courseServiceRest")
public class CourseServiceRestImpl implements CourseService {

	// NOTE: this restTemplate has been configured to ignore unknown json properties. Look at MessageConverterConfiguration for details.
	@Autowired
	@Qualifier(value = "oauth2RestTemplateClientCredentials")
	private OAuth2RestTemplate restTemplate;
	
	@Autowired
	@Qualifier(value = "basicRestServiceDiscovery")
	private ServiceDiscovery serviceDiscovery;

	@Override
	public Course addCourse(Course course) throws CourseTopicAlreadyExistException {

		String host = serviceDiscovery.getServiceUrl();

		String endpoint = host + "/courses";

		ResponseEntity response = restTemplate.postForEntity(endpoint, course, null);

		return course;
	}

	@Override
	public List<Course> findAll() {
		List<Course> courses = new ArrayList<Course>();

		String host = serviceDiscovery.getServiceUrl();

		String endpoint = host + "/courses";

		ResponseEntity<PagedResources<Resource<Course>>> pagedResourceResponse = restTemplate.exchange(endpoint, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<Resource<Course>>>() {
				});

		PagedResources<Resource<Course>> a = pagedResourceResponse.getBody();

		for (Resource<Course> rc : a.getContent()) {
			Course c = rc.getContent();

			courses.add(c);
		}

		return courses;
	}

	@Override
	public Page<Course> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findById(Long courseId) {

		Course course = null;

		String host = serviceDiscovery.getServiceUrl();

		String endpoint = host + "/courses/" + courseId;

		ResponseEntity<Resource<Course>> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<Course>>() {
				}, Collections.emptyMap());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Resource<Course> courseResource = responseEntity.getBody();

			course = courseResource.getContent();
		}

		return course;
	}

}
