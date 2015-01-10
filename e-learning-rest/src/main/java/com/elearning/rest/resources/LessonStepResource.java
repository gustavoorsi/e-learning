package com.elearning.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.LessonStep;
import com.elearning.rest.LessonRestController;

public class LessonStepResource extends ResourceSupport {

	private final LessonStep lessonStep;

	public LessonStepResource(LessonStep lessonStep) {
		this.lessonStep = lessonStep;
		this.add(linkTo(methodOn(LessonRestController.class).getLessonStep(lessonStep.getLesson().getId(), lessonStep.getId()))
				.withSelfRel());
	}

	public LessonStep getLessonStep() {
		return lessonStep;
	}

}
