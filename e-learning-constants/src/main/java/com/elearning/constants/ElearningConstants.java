package com.elearning.constants;

public final class ElearningConstants {
	
	
	public static final class API_SERVICE_1{
		
		public static final String API_PATH = "/api/v1/service1";

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
	
	
	public static final class API_SERVICE_2{
		
		public static final String API_PATH = "/api/v1/microservice2FooEndpoint";

		// security authentication endpoint
		public static final String AUTHENTICATE_URL = API_PATH + "/authenticate";

		// dummy rest endpoint

		public static final String FOO_ENDPOINT_BASE = API_PATH + "/foo";

		// Spring Boot Actuator services
		public static final String AUTOCONFIG_ENDPOINT = "/autoconfig";
		public static final String BEANS_ENDPOINT = "/beans";
		public static final String CONFIGPROPS_ENDPOINT = "/configprops";
		public static final String ENV_ENDPOINT = "/env";
		public static final String MAPPINGS_ENDPOINT = "/mappings";
		public static final String METRICS_ENDPOINT = "/metrics";
		public static final String SHUTDOWN_ENDPOINT = "/shutdown";
		
	}
	
	
	public static final class API_SERVICE_3{
		
		public static final String API_PATH = "/api/v1/microservice3SolrTestEndpoint";

		// dummy rest endpoint
		public static final String PRODUCT_ENDPOINT_BASE = API_PATH + "/products";
		public static final String GET_PRODUCT = "/{productId}";
		
	}
	
	

}
