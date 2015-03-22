package com.elearning.rest1.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_1.COURSES;
import static com.elearning.constants.ElearningConstants.API_SERVICE_1.GET_COURSE;
import static com.elearning.constants.ElearningConstants.API_SERVICE_1.GET_LESSONS_FOR_COURSE;
import static com.elearning.constants.ElearningConstants.API_SERVICE_1.POST_LESSON_TO_COURSE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.rest1.resources.CourseResource;
import com.elearning.rest1.resources.LessonResource;
import com.elearning.rest1.resources.assemblers.CourseResourceAssembler;
import com.elearning.rest1.resources.assemblers.LessonResourceAssembler;
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
// It states that all mapped methods will produce direct response output using @ResponseBody
@RequestMapping(COURSES)
public class CourseRestController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private CourseService courseService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private CourseResourceAssembler courseResourceAssembler;

	@Autowired
	private LessonResourceAssembler lessonResourceAssembler;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Return a "Page" of <code>CourseResourse</code>.
	 * 
	 * @return a Page of <code>CourseResourece</code> instance containing all courses.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/hal+json" })
	public HttpEntity<PagedResources<CourseResource>> getAll(//
			@PageableDefault(size = 10, page = 0) Pageable pageable, //
			PagedResourcesAssembler<Course> assembler //
	) {

		Page<Course> courses = this.courseService.findAll(pageable);

		return new ResponseEntity<>(assembler.toResource(courses, this.courseResourceAssembler), HttpStatus.OK);
	}

	@RequestMapping(value = GET_LESSONS_FOR_COURSE, method = RequestMethod.GET)
	public HttpEntity<PagedResources<LessonResource>> getLessonsForCourse( //
			@PathVariable Long courseId, //
			@PageableDefault(size = 10, page = 0) Pageable pageable, //
			PagedResourcesAssembler<Lesson> assembler//
	) {

		Course course = this.courseService.findById(courseId);

		Page<Lesson> lessons = this.lessonService.findByCourse(course, pageable);

		return new ResponseEntity<PagedResources<LessonResource>>(assembler.toResource(lessons, this.lessonResourceAssembler), HttpStatus.OK);
	}

	/**
	 * Get a course based on the path variable <code>courseId</code>. If the course is not found then return a 404 with an message
	 * explanation. (see <code>ExceptionControllerAdvice</code> for more detail.)
	 * 
	 * @param courseId
	 * @return a <code>CourseResource</code>
	 */
	@RequestMapping(value = GET_COURSE, method = RequestMethod.GET)
	public HttpEntity<CourseResource> getCourse(//
			@PathVariable Long courseId//
	) {

		Course course = this.courseService.findById(courseId);
		return new ResponseEntity<CourseResource>(this.courseResourceAssembler.toResource(course), HttpStatus.OK);
	}

	/**
	 * Add a new course only if there is no other with the same topic.
	 * 
	 * @param input
	 * @return a 201 response code and the new resource uri or a <code>HttpStatus.FORBIDDEN</code> in case the course already exist.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpEntity<?> add(@RequestBody Course input) {

		Course course = this.courseService.addCourse(input);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(methodOn(CourseRestController.class, course.getId()).getCourse(course.getId())).toUri());

		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

	/**
	 * Adds a new lesson to an existing course.
	 * 
	 * @param courseId
	 * @param input
	 * @return a 201 response code and the new resource uri or a <code>HttpStatus.NOT_FOUND</code> in case the course does not exist.
	 */
	@RequestMapping(value = POST_LESSON_TO_COURSE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public HttpEntity<?> addLesson( //
			@PathVariable Long courseId, //
			@RequestBody Lesson input //
	) {

		Lesson lesson = this.lessonService.addLesson(courseId, input);

		HttpHeaders httpHeaders = new HttpHeaders();

		String href = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(LessonRestController.class, lesson.getId()).getLesson(lesson.getId()))
				.withSelfRel().getHref();
		httpHeaders.setLocation(ServletUriComponentsBuilder.fromPath(href).build().toUri());

		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

}
