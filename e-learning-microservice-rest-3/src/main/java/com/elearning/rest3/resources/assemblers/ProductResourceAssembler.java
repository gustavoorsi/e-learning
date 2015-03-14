package com.elearning.rest3.resources.assemblers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.elearning.rest3.domain.Product;
import com.elearning.rest3.endpoints.Rest3Controller;
import com.elearning.rest3.resources.ProductResource;

@Component
public class ProductResourceAssembler implements ResourceAssembler<Product, ProductResource> {

	@Override
	public ProductResource toResource(Product product) {

		ProductResource resource = new ProductResource(product);

		// add link to itself ( rel = self )
		Link selfLink = linkTo(methodOn(Rest3Controller.class).getProduct(product.getId())).withSelfRel();
		resource.add(selfLink);

		return resource;
	}

}
