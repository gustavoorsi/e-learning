package com.elearning.rest1.resources;

import org.springframework.hateoas.Resource;

import com.elearning.model.entities.Foo;

/**
 * Wrapper for Foo. Here we add some useful links.
 * 
 * @author Gustavo Orsi
 *
 */
public class FooResource extends Resource<Foo> {

	public FooResource(Foo content) {
		super(content);
	}

}
