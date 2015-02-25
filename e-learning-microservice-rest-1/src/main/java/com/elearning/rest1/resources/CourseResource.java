package com.elearning.rest1.resources;

import org.springframework.hateoas.Resource;

import com.elearning.model.entities.Course;

/**
 * Wrapper for Course. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class CourseResource extends Resource<Course> {
	
	
	public CourseResource( Course content ){
		super(content);
	}

}
