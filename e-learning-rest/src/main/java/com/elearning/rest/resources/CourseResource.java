package com.elearning.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.Course;
import com.elearning.rest.CourseRestController;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseResource extends ResourceSupport {

	private Course course;

	public CourseResource() {
	}

	public CourseResource(Course course) {
		this.course = course;
		this.add(linkTo(methodOn(CourseRestController.class).getCourse(course.getId())).withSelfRel());
	}

	public Course getCourse() {
		return course;
	}

}
