package com.elearning.model.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.RootApplication;
import com.elearning.model.entities.LessonStep;
import com.elearning.model.entities.User;
import com.elearning.model.persistence.jparepositories.LessonStepRepository;
import com.elearning.model.persistence.jparepositories.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RootApplication.class)
@ActiveProfiles(profiles = "test")
public class ModelApplicationTests {

	@Autowired
	private LessonStepRepository lessonStepRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {

		List<LessonStep> lessonSteps = lessonStepRepository.findAll();

		System.out.println("List lesson steps: ");

		for (LessonStep ls : lessonSteps) {
			System.out.println(ls);
		}

		List<User> users = this.userRepository.findAll();

		System.out.println("List users: ");

		for (User u : users) {
			System.out.println(u);
		}

	}

}
