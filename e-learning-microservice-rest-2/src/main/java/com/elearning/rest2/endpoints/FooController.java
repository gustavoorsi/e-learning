package com.elearning.rest2.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_2.FOO_ENDPOINT_BASE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.rest2.domain.Foo;
import com.elearning.rest2.resources.FooResource;
import com.elearning.rest2.resources.assemblers.FooResourceAssembler;

/**
 * 
 * Rest endpoint.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping(FOO_ENDPOINT_BASE)
public class FooController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private FooResourceAssembler fooResourceAssembler;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Test method
	 * 
	 * @return a foo
	 */
	@PreAuthorize("hasAuthority('ROLE_DOMAIN_USER')")
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<FooResource> getFoo() {

		Foo foo = new Foo(1L, "a Foo");
		return new ResponseEntity<FooResource>(this.fooResourceAssembler.toResource(foo), HttpStatus.OK);
	}

}
