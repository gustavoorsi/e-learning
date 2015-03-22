package com.elearning.rest1.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_1.FOOS;
import static com.elearning.constants.ElearningConstants.API_SERVICE_1.GET_FOO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.model.entities.Foo;
import com.elearning.rest1.resources.FooResource;
import com.elearning.rest1.resources.assemblers.FooResourceAssembler;
import com.elearning.service.FooService;

@RestController
@RequestMapping(FOOS)
//@PreAuthorize("hasRole('ROLE_CLIENT')") // This endpoint is only accessible by users with role 'ROLE_CLIENT'.
@PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_USER')") // This endpoint is only accessible by users with role 'ROLE_CLIENT' or 'ROLE_USER'
public class FooRestController {

	@Autowired
	private FooService fooService;

	@Autowired
	private FooResourceAssembler fooResourceAssembler;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/hal+json" })
	public HttpEntity<PagedResources<FooResource>> getAll(//
			@PageableDefault(size = 10, page = 0) Pageable pageable, //
			PagedResourcesAssembler<Foo> assembler//
	) {

		Page<Foo> foos = this.fooService.findAll(pageable);

		return new ResponseEntity<>(assembler.toResource(foos, this.fooResourceAssembler), HttpStatus.OK);
	}

	@RequestMapping(value = GET_FOO, method = RequestMethod.GET)
	public HttpEntity<FooResource> getFoo(//
			@PathVariable Long fooId//
	) {

		Foo foo = this.fooService.findById(fooId);

		return new ResponseEntity<FooResource>(this.fooResourceAssembler.toResource(foo), HttpStatus.OK);
	}

}
