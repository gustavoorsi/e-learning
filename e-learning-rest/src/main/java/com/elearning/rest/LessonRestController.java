package com.elearning.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.exception.LessonNotFoundException;
import com.elearning.model.Lesson;
import com.elearning.model.LessonStep;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.persistence.jparepositories.LessonStepRepository;
import com.elearning.rest.resources.LessonResource;
import com.elearning.rest.resources.LessonStepResource;

@RestController
@RequestMapping("/lessons")
public class LessonRestController {

	@Autowired
	private LessonStepRepository lessonStepRepository;

	@Autowired
	private LessonRepository lessonRepository;

	/**
	 * 
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "/{lessonId}", method = RequestMethod.GET)
	public LessonResource getLesson(@PathVariable Long lessonId) {

		Lesson lesson = this.lessonRepository.findById(lessonId).orElseThrow(() -> new LessonNotFoundException(lessonId));

		return new LessonResource(lesson);
	}

	/**
	 * 
	 * @param lessonId
	 * @return
	 */
	@RequestMapping(value = "/{lessonId}", params = "includeSteps", method = RequestMethod.GET)
	public LessonResource getDeepLesson(@PathVariable Long lessonId) {

		Lesson l = this.lessonRepository.findByIdAndFetchLessonStepsEagerly(lessonId);

		return new LessonResource(l);
	}

	/**
	 * 
	 * @param lessonId
	 * @param lessonStepId
	 * @return
	 */
	@RequestMapping(value = "/{lessonId}/steps/{lessonStepId}", method = RequestMethod.GET)
	public LessonStepResource getLessonStep(@PathVariable Long lessonId, @PathVariable Long lessonStepId) {

		Lesson lesson = this.lessonRepository.findOne(lessonId);

		LessonStep lessonStep = this.lessonStepRepository.findByLessonAndId(lesson, lessonStepId);

		return new LessonStepResource(lessonStep);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<LessonResource> getAllLessons() {

		List<LessonResource> lessonResources = new ArrayList<LessonResource>();

		for (Lesson l : this.lessonRepository.findAll()) {
			lessonResources.add(new LessonResource(l));
		}

		return lessonResources;
	}

}
