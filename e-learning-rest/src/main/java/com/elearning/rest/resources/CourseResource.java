package com.elearning.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.Course;
import com.elearning.rest.CourseRestController;

public class CourseResource extends ResourceSupport {

	private final Course course;

	public CourseResource(Course course) {
		this.course = course;
		this.add(linkTo(methodOn(CourseRestController.class).getCourse(course.getId())).withSelfRel());
	}

	public Course getCourse() {
		return course;
	}

}
