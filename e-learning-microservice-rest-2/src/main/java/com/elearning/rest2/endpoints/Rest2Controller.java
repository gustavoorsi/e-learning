package com.elearning.rest2.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * Rest endpoint.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping("/rest2")
public class Rest2Controller {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Test method
	 * 
	 * @return a foo string.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getFoo() {
		return "foo - testing commit to git, trigger jenkins and finally make jenkins to set automatically build status on git.";
	}

}
