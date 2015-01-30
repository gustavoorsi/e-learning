package com.elearning.rest1.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;
import com.elearning.model.exception.EntityNotFoundException;
import com.elearning.model.exception.LessonNotFoundException;
import com.elearning.model.persistence.jparepositories.LessonRepository;
import com.elearning.model.persistence.jparepositories.LessonStepRepository;
import com.elearning.rest1.resources.LessonResource;
import com.elearning.rest1.resources.LessonResources;
import com.elearning.rest1.resources.LessonStepResource;

/**
 * 
 * Rest endpoint that handles all Lesson related actions.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping("/lessons")
public class LessonRestController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private LessonStepRepository lessonStepRepository;

	@Autowired
	private LessonRepository lessonRepository;

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
	@RequestMapping(value = "/{lessonId}", method = RequestMethod.GET)
	public LessonResource getLesson(@PathVariable Long lessonId) {

		Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));

		return new LessonResource(lesson);
	}

	/**
	 * 
	 * Get a lesson including the lesson steps.
	 * 
	 * @param lessonId
	 * @return a LessonResource including the lessonStep if the lesson was found or a 404 status code with "lesson not found" message.
	 */
	@RequestMapping(value = "/{lessonId}", params = "includeSteps", method = RequestMethod.GET)
	public LessonResource getDeepLesson(@PathVariable Long lessonId) {

		Lesson l = this.lessonRepository.findByIdAndFetchLessonStepsEagerly(lessonId);

		return new LessonResource(l);
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
	public LessonStepResource getLessonStep(@PathVariable Long lessonId, @PathVariable Long lessonStepId) {

		Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));

		LessonStep lessonStep = this.lessonStepRepository.findByLessonAndId(lesson, lessonStepId).orElseThrow(
				() -> new EntityNotFoundException(lessonStepId));
		;

		return new LessonStepResource(lessonStep);
	}

	/**
	 * Return a list of <code>LessonResourse</code> instances wrapped in a <code>LessonResources</code> object.
	 * 
	 * @return a <code>LessonResources</code> instance containing all the lessons.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public LessonResources getAllLessons() {

		List<LessonResource> lessonResources = new ArrayList<LessonResource>();

		// using lambda to wrap courses into CourseResource.
		this.lessonRepository.findAll().stream().map(p -> new LessonResource(p)).forEach(p -> lessonResources.add(p));

		return new LessonResources(lessonResources);
	}

}
