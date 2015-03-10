package com.elearning.rest1.endpoints;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.RootTestApplication;
import com.elearning.model.entities.Course;
import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;
import com.elearning.model.persistence.jparepositories.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootTestApplication.class })
@WebAppConfiguration
public abstract class AbstractRestControllerTest {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	protected CourseRepository courseRepositoryMock;

	@Before
	public void setup() {
		Mockito.reset(courseRepositoryMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected List<Course> getTestCourseList() {

		Course course1 = new Course("Course 1");
		Course course2 = new Course("Course 2");
		Course course3 = new Course("Course 3");

		return Arrays.asList(course1, course2, course3);
	}

	protected List<Lesson> getTestLessonList() {
		Lesson lesson1 = new Lesson("Lesson 1");
		Lesson lesson2 = new Lesson("Lesson 2");
		Lesson lesson3 = new Lesson("Lesson 3");

		return Arrays.asList(lesson1, lesson2, lesson3);
	}

	protected List<LessonStep> getTestLessonStepList() {
		LessonStep lessonStep1 = new LessonStep("Lesson Step 1");
		LessonStep lessonStep2 = new LessonStep("Lesson Step 2");
		LessonStep lessonStep3 = new LessonStep("Lesson Step 3");

		return Arrays.asList(lessonStep1, lessonStep2, lessonStep3);
	}

}
