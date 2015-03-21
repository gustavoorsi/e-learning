package com.elearning.rest1.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_1.GET_LESSON;
import static com.elearning.constants.ElearningConstants.API_SERVICE_1.LESSONS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;
import com.elearning.rest1.resources.LessonResource;
import com.elearning.rest1.resources.LessonStepResource;
import com.elearning.rest1.resources.assemblers.LessonResourceAssembler;
import com.elearning.rest1.resources.assemblers.LessonStepResourceAssembler;
import com.elearning.service.LessonService;
import com.elearning.service.LessonStepService;

/**
 * 
 * Rest endpoint that handles all Lesson related actions.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping(LESSONS)
public class LessonRestController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private LessonService lessonService;

	@Autowired
	private LessonStepService lessonStepService;

	@Autowired
	private LessonResourceAssembler lessonResourceAssembler;

	@Autowired
	private LessonStepResourceAssembler lessonStepResourceAssembler;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * 
	 * Get a lesson by its id.
	 * 
	 * @param lessonId
	 * @return a LessonResource if the lesson was found or a 404 status code with "lesson not found" message.
	 */
	@RequestMapping(value = GET_LESSON, method = RequestMethod.GET)
	public HttpEntity<LessonResource> getLesson(//
			@PathVariable Long lessonId) {

		Lesson lesson = this.lessonService.findById(lessonId);

		return new ResponseEntity<LessonResource>(this.lessonResourceAssembler.toResource(lesson), HttpStatus.OK);
	}

	/**
	 * 
	 * Get a lesson including the lesson steps.
	 * 
	 * @param lessonId
	 * @return a LessonResource including the lessonStep if the lesson was found or a 404 status code with "lesson not found" message.
	 */
	@RequestMapping(value = "/{lessonId}", params = "includeSteps", method = RequestMethod.GET)
	public HttpEntity<LessonResource> getDeepLesson(//
			@PathVariable Long lessonId//
	) {

		Lesson lesson = this.lessonService.findByIdAndFetchLessonStepsEagerly(lessonId);

		return new ResponseEntity<LessonResource>(this.lessonResourceAssembler.toResource(lesson), HttpStatus.OK);
	}

	/**
	 * 
	 * Get a lessonStep. If lesson is not found return a 404 status code with "lesson not found" message. If lessonStep is not found return
	 * a 404 status code with "lessonStep not found" message.
	 * 
	 * @param lessonId
	 * @param lessonStepId
	 * @return a LessonStepResource if the step and lesson are found and if the step belong to the lesson.
	 */
	@RequestMapping(value = "/{lessonId}/steps/{lessonStepId}", method = RequestMethod.GET)
	public HttpEntity<LessonStepResource> getLessonStep(//
			@PathVariable Long lessonId, //
			@PathVariable Long lessonStepId//
	) {

		Lesson lesson = this.lessonService.findById(lessonId);
		LessonStep lessonStep = this.lessonStepService.findByLessonAndId(lesson, lessonStepId);

		return new ResponseEntity<LessonStepResource>(this.lessonStepResourceAssembler.toResource(lessonStep), HttpStatus.OK);
	}

	/**
	 * Return a list of <code>LessonResourse</code> instances wrapped in a <code>LessonResources</code> object.
	 * 
	 * @return a <code>LessonResources</code> instance containing all the lessons.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<PagedResources<LessonResource>> getAllLessons(//
			@PageableDefault(size = 10, page = 0) Pageable pageable, //
			PagedResourcesAssembler<Lesson> assembler//
	) {

		Page<Lesson> lessons = this.lessonService.findAll(pageable);

		return new ResponseEntity<PagedResources<LessonResource>>(assembler.toResource(lessons, this.lessonResourceAssembler), HttpStatus.OK);
	}

}
