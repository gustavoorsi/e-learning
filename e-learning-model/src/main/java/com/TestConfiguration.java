package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.model.LessonStep;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.persistence.jparepositories.LessonStepRepository;

@Configuration
@Profile("test")
public class TestConfiguration {

	// populate db with some test date.
	@Bean
	CommandLineRunner init(LessonStepRepository lessonStepRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {

		return (evt) -> {

			Course courseOne = new Course("Test 1");
			Lesson lessonOne = new Lesson("Test 2");
			Lesson lessonTwo = new Lesson("Test 3");

			lessonOne.addLessonStep(new LessonStep("get started with spring boot"))
					.addLessonStep(new LessonStep("eclipse and spring boot"))
					.addLessonStep(new LessonStep("setup a new project with spring boot"));

			lessonTwo.addLessonStep(new LessonStep("What is spring Hateoas?"))
					.addLessonStep(new LessonStep("some spring Hateoas examples"));

			courseOne.addLesson(lessonOne).addLesson(lessonTwo);

			courseRepository.save(courseOne);

			// ///////////////////////////////////////////////////////////////////

			Course courseTwo = new Course("Hibernate");
			Lesson courseTwoLessonOne = new Lesson("Hibernate configuration");

			courseTwoLessonOne.addLessonStep(new LessonStep("config using xml")).addLessonStep(new LessonStep("config using annotations"))
					.addLessonStep(new LessonStep("test your configuration"));

			courseTwo.addLesson(courseTwoLessonOne);

			courseRepository.save(courseTwo);

		};
	}

}
