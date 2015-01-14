package com.elearning.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elearning.exception.CourseNotFoundException;
import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.rest.resources.CourseResource;
import com.elearning.rest.resources.CourseResources;
import com.elearning.service.CourseService;
import com.elearning.service.LessonService;

/**
 * 
 * Rest endpoint that handles all course related actions.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping("/courses")
public class CourseRestController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//
	
	@Autowired
	private CourseService courseService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LessonRepository lessonRepository;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Return a list of <code>CourseResourse</code> instances wrapped in a <code>CourseResources</code> object.
	 * 
	 * @return a <code>CourseResoureces</code> instance containing all courses.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public CourseResources getAll() {

		List<CourseResource> courseResources = new ArrayList<CourseResource>();

		List<Course> courses = this.courseRepository.findAll();

		// using lambda to wrap courses into CourseResource.
		courses.stream().map(p -> new CourseResource(p)).forEach(p -> courseResources.add(p));

		return new CourseResources(courseResources);
	}
	
	
	/**
	 * Just a stupid method to show the use of lambda expression/statements.
	 * 
	 * @param filterByLessonCount
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, params = "filterByLessonCount")
	public CourseResources getAllWithMoreThan2Lessons(
			@RequestParam(defaultValue = "2", value = "filterByLessonCount") int filterByLessonCount) {

		List<CourseResource> courseResources = new ArrayList<CourseResource>();

		List<Course> courses = this.courseRepository.findAll();

		// using lambda to wrap courses into CourseResource and filter out those that don't comply with the requirement.
		courses.stream().filter(p -> (p.getLessons().size() > filterByLessonCount)).map(p -> new CourseResource(p))
				.forEach(p -> courseResources.add(p));

		return new CourseResources(courseResources);
	}

	/**
	 * Get a course based on the path variable <code>courseId</code>. If the course is not found then return a 404 with an message
	 * explanation. (see <code>ExceptionControllerAdvice</code> for more detail.)
	 * 
	 * @param courseId
	 * @return a <code>CourseResource</code>
	 */
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public CourseResource getCourse(@PathVariable Long courseId) {

		// note: courseRepository returns an Option instance, so we can use the utility method .orElseThrow() and lambda expression.
		Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		return new CourseResource(course);
	}

	/**
	 * Add a new course only if there is no other with the same topic. 
	 * 
	 * @param input
	 * @return a 201 response code and the new resource uri or a <code>HttpStatus.FORBIDDEN</code> in case the course already exist.
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> add(@RequestBody Course input) {

		Course course = this.courseService.addCourse(input);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(course.getId()).toUri());

		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}

	/**
	 * Adds a new lesson to an existing course.
	 * 
	 * @param courseId
	 * @param input
	 * @return a 201 response code and the new resource uri or a <code>HttpStatus.NOT_FOUND</code> in case the course does not exist.
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
