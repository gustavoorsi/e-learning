package com.elearning.rest.resources;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.ResourceSupport;

import com.elearning.model.Lesson;
import com.elearning.rest.LessonRestController;

public class LessonResource extends ResourceSupport {

	private final Lesson lesson;

	public LessonResource(Lesson lesson) {
		this.lesson = lesson;
		this.add(linkTo(methodOn(LessonRestController.class).getLesson(lesson.getId())).withSelfRel());
		this.add(linkTo(methodOn(LessonRestController.class).getAllLessons()).withRel( "all-lessons" ));
	}

	public Lesson getLesson() {
		return lesson;
	}

}
