package com;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.elearning.model.LessonStep;
import com.elearning.persistence.jparepositories.LessonStepRepository;

@ComponentScan
@EnableAutoConfiguration
public class ModelApplication {

	public static void main(String[] args) {

//		args = new String[]{"-D spring_profiles_default=test"};
		
		ConfigurableApplicationContext ctx = SpringApplication.run(ModelApplication.class, args);

		LessonStepRepository lessonStepRepository = ctx.getBean(LessonStepRepository.class);

		List<LessonStep> lessonSteps = lessonStepRepository.findAll();

		System.out.println("List lesson steps: ");

		for (LessonStep ls : lessonSteps) {
			System.out.println(ls);
		}

	}

}
