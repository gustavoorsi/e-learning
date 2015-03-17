package com.elearning.rest1.endpoints.constantURLs;

public abstract class ConstantEndpointURLs {
	private static final String API_PATH = "/api/v1/microservice1Endpoint";

	// COURSES
	public static final String COURSES = API_PATH + "/courses";
	public static final String GET_LESSONS_FOR_COURSE = "/{courseId}/lessons";
	public static final String GET_COURSE = "/{courseId}";
	public static final String POST_LESSON_TO_COURSE = "/{courseId}/lesson";

	// LESSONS
	public static final String LESSONS = API_PATH + "/lessons";
	public static final String GET_LESSON = "/{lessonId}";
	
	// FOO
	public static final String FOOS = API_PATH + "/foos";
	public static final String GET_FOO = "/{fooId}";

}
