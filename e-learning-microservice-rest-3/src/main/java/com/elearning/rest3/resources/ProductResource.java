package com.elearning.rest3.resources;

import org.springframework.hateoas.Resource;

import com.elearning.rest3.domain.Product;

/**
 * Wrapper for Foo. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class ProductResource extends Resource<Product> {
	
	
	public ProductResource( Product content ){
		super(content);
	}

}
