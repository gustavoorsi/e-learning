package com.elearning.rest2.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.rest2.domain.Foo;
import com.elearning.rest2.endpoints.FooController;
import com.elearning.rest2.resources.FooResource;

@Component
public class FooResourceAssembler implements ResourceAssembler<Foo, FooResource> {

	@Override
	public FooResource toResource(Foo foo) {

		FooResource resource = new FooResource(foo);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(FooController.class).getFoo()).withSelfRel();
		resource.add(selfLink);

		return resource;
	}

}
