package com.elearning.rest1.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_1.COURSES;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;

import com.elearning.model.entities.Course;

public class CourseRestControllerTest extends AbstractRestControllerTest {

	@Test
	public void getCourses_ShouldReturnAllCourses() throws Exception {
		Page<Course> page = new PageImpl<Course>(getTestCourseList(), new PageRequest(0, 10), 3);

		when(courseRepositoryMock.findAll(any(Pageable.class))).thenReturn(page);

		mockMvc.perform(get(COURSES)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType(MediaTypes.HAL_JSON)) //
				.andExpect(jsonPath("$._embedded.courses", hasSize(2))) //
				.andExpect(jsonPath("$._embedded.courses[0].courseTopic", is("Test Course 1"))) //
				.andExpect(jsonPath("$._embedded.courses[1].courseTopic", is("Test Course 2"))) //
				.andExpect(jsonPath("$._links.self.templated", is(true))) //
				.andExpect(jsonPath("$._links.self.href", endsWith("/courses" + "{?page,size,sort}")));
	}

}
