package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.model.LessonStep;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.persistence.jparepositories.LessonStepRepository;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ModelApplication {

	// public static void main(String[] args) {
	// SpringApplication.run(ModelApplication.class, args);
	// }

	// populate db with some test date.
	@Bean
	CommandLineRunner init(LessonStepRepository lessonStepRepository, LessonRepository lessonRepository, CourseRepository courseRepository) {

		return (evt) -> {

			Course courseOne = new Course("Spring 4");
			Lesson lessonOne = new Lesson("Spring boot");
			Lesson lessonTwo = new Lesson("Spring Hateoas");

			lessonOne.addLessonStep(new LessonStep("get started with spring boot"))
					.addLessonStep(new LessonStep("eclipse and spring boot"))
					.addLessonStep(new LessonStep("setup a new project with spring boot"));

			lessonTwo.addLessonStep(new LessonStep("What is spring Hateoas?"))
					.addLessonStep(new LessonStep("some spring Hateoas examples"));

			courseOne.addLesson(lessonOne).addLesson(lessonTwo);

			courseRepository.save(courseOne);

		};
	}
}
