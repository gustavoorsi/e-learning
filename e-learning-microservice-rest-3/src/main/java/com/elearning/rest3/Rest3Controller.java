package com.elearning.rest3;

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
@RequestMapping("/rest3")
public class Rest3Controller {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Return a bar string.
	 * 
	 * @return a bar string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getBar() {
		return "bar";
	}

}
