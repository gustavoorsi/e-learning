package com.elearning.rest1.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.model.entities.Lesson;
import com.elearning.rest1.endpoints.LessonRestController;
import com.elearning.rest1.resources.LessonResource;

@Component
public class LessonResourceAssembler implements ResourceAssembler<Lesson, LessonResource> {

	@Override
	public LessonResource toResource(Lesson lesson) {

		LessonResource resource = new LessonResource(lesson);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(LessonRestController.class).getLesson(lesson.getId())).withSelfRel();
		resource.add(selfLink);

		return resource;
	}

}
