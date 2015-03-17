package com.elearning.rest1.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.model.entities.Foo;
import com.elearning.rest1.endpoints.FooRestController;
import com.elearning.rest1.resources.FooResource;

@Component
public class FooResourceAssembler implements ResourceAssembler<Foo, FooResource> {

	@Override
	public FooResource toResource(Foo foo) {

		FooResource resource = new FooResource(foo);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(FooRestController.class).getFoo(foo.getId())).withSelfRel();
		resource.add(selfLink);

		return resource;
	}

}
