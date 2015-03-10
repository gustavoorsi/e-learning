/* 
 * Copyright 2013-2014 JIWHIZ Consulting Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elearning.rest1.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.elearning.model.persistence.jparepositories.CourseRepository;
import com.elearning.model.persistence.jparepositories.LessonRepository;
import com.elearning.model.persistence.jparepositories.LessonStepRepository;
import com.elearning.service.CourseService;
import com.elearning.service.LessonService;
import com.elearning.service.LessonStepService;

@Configuration
public class MocksConfig {

	@Bean
	public CourseRepository courseRepositoryMock() {
		return Mockito.mock(CourseRepository.class);
	}

	@Bean
	public LessonRepository lessonRepositoryMock() {
		return Mockito.mock(LessonRepository.class);
	}

	@Bean
	public LessonStepRepository lessonStepRepositoryMock() {
		return Mockito.mock(LessonStepRepository.class);
	}

	@Bean
	public CourseService courseServiceMock() {
		return Mockito.mock(CourseService.class);
	}

	@Bean
	public LessonService lessonServiceMock() {
		return Mockito.mock(LessonService.class);
	}

	@Bean
	public LessonStepService lessonStepServiceMock() {
		return Mockito.mock(LessonStepService.class);
	}

}
