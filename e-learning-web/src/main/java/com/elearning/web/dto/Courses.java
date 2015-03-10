package com.elearning.web.dto;

import java.util.List;

import org.springframework.hateoas.Resource;

import com.elearning.model.entities.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Courses {

	private List<Resource<Course>> course;

	public List<Resource<Course>> getCourse() {
		return course;
	}

	public void setCourse(List<Resource<Course>> course) {
		this.course = course;
	}

}
