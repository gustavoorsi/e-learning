package com.elearning.rest1.resources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.entities.Course;

/**
 * Wrapper for Course. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseResource extends ResourceSupport {

	private Course course;

	public CourseResource() {
	}

	public CourseResource(Course course, Rest1ServiceEurekaLinkCreator eurekaLinkCreator) {
		this.course = course;

		// get server uri using eureka and hystrix to handle server fault tolerance.
		String serverUri = eurekaLinkCreator.getServerUri();

		this.add(new Link(serverUri + "/courses/" + course.getId()).withSelfRel()); // TODO: improve this nasty link creating.

		// this.add(linkTo(methodOn(CourseRestController.class).getCourse(course.getId())).withSelfRel());
	}

	public Course getCourse() {
		return course;
	}

}
