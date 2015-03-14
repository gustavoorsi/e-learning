package com.elearning.rest3.endpoints.constantURLs;

public abstract class ConstantEndpointURLs {
	private static final String API_PATH = "/api/v1/microservice3SolrTestEndpoint";

	// dummy rest endpoint
	public static final String PRODUCT_ENDPOINT_BASE = API_PATH + "/products";
	public static final String GET_PRODUCT = "/{productId}";

}
