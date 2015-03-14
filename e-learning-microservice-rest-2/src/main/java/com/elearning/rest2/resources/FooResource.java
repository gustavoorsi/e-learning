package com.elearning.rest2.resources;

import org.springframework.hateoas.Resource;

import com.elearning.rest2.domain.Foo;

/**
 * Wrapper for Foo. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class FooResource extends Resource<Foo> {
	
	
	public FooResource( Foo content ){
		super(content);
	}

}
