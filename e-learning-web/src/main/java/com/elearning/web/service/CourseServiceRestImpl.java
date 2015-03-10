package com.elearning.web.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.elearning.model.entities.Course;
import com.elearning.model.exception.CourseTopicAlreadyExistException;
import com.elearning.service.CourseService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

@Service(value = "courseServiceRest")
public class CourseServiceRestImpl implements CourseService {

	@Autowired
	@Qualifier(value = "restTemplate")
	private RestTemplate restTemplate; // NOTE: this restTemplate has been configured to ignore unknown json properties. Look at
										// WebMvcConfigureation for details.

	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public Course addCourse(Course course) throws CourseTopicAlreadyExistException {

		InstanceInfo instanceInfo = this.discoveryClient.getNextServerFromEureka("E-LEARNING-MICROSERVICE-REST-1", false);

		String hostname = instanceInfo.getHomePageUrl();

		String endpoint = hostname + "courses";

		ResponseEntity response = restTemplate.postForEntity(endpoint, course, null);

		return course;
	}

	@Override
	public List<Course> findAll() {
		List<Course> courses = new ArrayList<Course>();

		InstanceInfo instanceInfo = this.discoveryClient.getNextServerFromEureka("E-LEARNING-MICROSERVICE-REST-1", false);

		String hostname = instanceInfo.getHomePageUrl();

		String endpoint = hostname + "courses";

		ResponseEntity<PagedResources<Resource<Course>>> pagedResourceResponse2 = restTemplate.exchange(endpoint, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<Resource<Course>>>() {
				});

		PagedResources<Resource<Course>> a = pagedResourceResponse2.getBody();

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

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<Resource<Course>> responseEntity = restTemplate.exchange("http://localhost:8081/courses/" + courseId, HttpMethod.GET, null,
				new ParameterizedTypeReference<Resource<Course>>() {
				}, Collections.emptyMap());

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			Resource<Course> courseResource = responseEntity.getBody();

			course = courseResource.getContent();
		}

		return course;
	}

}
