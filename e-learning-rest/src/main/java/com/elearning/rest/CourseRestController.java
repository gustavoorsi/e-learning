package com.elearning.rest;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elearning.exception.CourseNotFoundException;
import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.rest.resources.CourseResource;
import com.elearning.service.CourseService;
import com.elearning.service.LessonService;

@RestController
@RequestMapping("/courses")
public class CourseRestController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LessonRepository lessonRepository;
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET )
	public Collection<CourseResource> getAll(){
		Collection<CourseResource> courseResources = new ArrayList<CourseResource>();
		
		for( Course c : this.courseRepository.findAll() ){
			courseResources.add( new CourseResource(c) );
		}
		
		return courseResources;
	}
	

	/**
	 * 
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public CourseResource getCourse(@PathVariable Long courseId) {

		Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		return new CourseResource(course);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Course input) {

		Course course = this.courseService.addCourse(input);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri());

		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param courseId
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/{courseId}/lesson", method = RequestMethod.POST)
	ResponseEntity<?> addLesson(@PathVariable Long courseId, @RequestBody Lesson input) {
		
		Lesson lesson = this.lessonService.addLesson(courseId, input);

		HttpHeaders httpHeaders = new HttpHeaders();

		String href = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(LessonRestController.class, lesson.getId()).getLesson(lesson.getId())).withSelfRel()
				.getHref();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromPath(href).build().toUri());
		// httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(lesson.getId()).toUri());

		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

}
