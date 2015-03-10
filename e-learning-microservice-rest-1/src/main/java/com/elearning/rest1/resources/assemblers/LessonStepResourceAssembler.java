package com.elearning.rest1.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.model.entities.LessonStep;
import com.elearning.rest1.endpoints.LessonStepRestController;
import com.elearning.rest1.resources.LessonStepResource;

@Component
public class LessonStepResourceAssembler implements ResourceAssembler<LessonStep, LessonStepResource> {

	@Override
	public LessonStepResource toResource(LessonStep lessonStep) {

		LessonStepResource resource = new LessonStepResource(lessonStep);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(LessonStepRestController.class).getLessonStep(lessonStep.getId())).withSelfRel();
		resource.add(selfLink);

		return resource;
	}

}
