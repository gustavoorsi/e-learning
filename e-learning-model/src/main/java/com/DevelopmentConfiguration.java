package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.elearning.model.Course;
import com.elearning.model.Lesson;
import com.elearning.model.LessonStep;
import com.elearning.model.User;
import com.elearning.persistence.jparepositories.CourseRepository;
import com.elearning.persistence.jparepositories.LessonRepository;
import com.elearning.persistence.jparepositories.LessonStepRepository;
import com.elearning.persistence.jparepositories.UserRepository;

@Configuration
// @Profile("development")
public class DevelopmentConfiguration {

	// populate db with some test date.
	@Bean
	CommandLineRunner init(LessonStepRepository lessonStepRepository, LessonRepository lessonRepository, CourseRepository courseRepository,
			UserRepository userRepository) {

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

			// ///////////////////////////////////////////////////////////////////

			Course courseTwo = new Course("Hibernate");
			Lesson courseTwoLessonOne = new Lesson("Hibernate configuration");

			courseTwoLessonOne.addLessonStep(new LessonStep("config using xml")).addLessonStep(new LessonStep("config using annotations"))
					.addLessonStep(new LessonStep("test your configuration"));

			courseTwo.addLesson(courseTwoLessonOne);

			courseRepository.save(courseTwo);

			// ///////////////////////////////////////////////////////////////////

			User user = new User();
			user.setFirstName("Gustavo");
			user.setLastName("Orsi");
			user.setUsername("gustavoorsi");
			user.setPassword("123456");

			userRepository.save(user);

			user.setFirstName("David");
			user.setLastName("Silver");
			user.setUsername("david1");
			user.setPassword("654321");

			userRepository.save(user);
		};
	}
}
