package com.elearning.rest1.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.rest1.endpoints.CourseRestController;
import com.elearning.rest1.resources.CourseResource;

@Component
public class CourseResourceAssembler implements ResourceAssembler<Course, CourseResource> {

	public static final String LINK_NAME_LESSONS = "lessons";

	@Autowired
	private PagedResourcesAssembler<Lesson> assembler;

	@Override
	public CourseResource toResource(Course course) {

		CourseResource resource = new CourseResource(course);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(CourseRestController.class).getCourse(course.getId())).withSelfRel();
		resource.add(selfLink);

		// add link to list of lessons for this course.
		
//		URI uri = fromMethodCall(on(CourseRestController.class).getLessonsForCourse(course.getId(), null, null)).buildAndExpand(1).toUri();
		Link lessonsLink = linkTo(methodOn(CourseRestController.class).getLessonsForCourse(course.getId(), null, null)).withRel(LINK_NAME_LESSONS);
		resource.add(assembler.appendPaginationParameterTemplates(lessonsLink));

		return resource;
	}

}
