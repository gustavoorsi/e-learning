package com.elearning.rest1.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.model.entities.LessonStep;
import com.elearning.rest1.resources.LessonStepResource;
import com.elearning.rest1.resources.assemblers.LessonStepResourceAssembler;
import com.elearning.service.LessonStepService;

/**
 * 
 * Rest endpoint that handles all LessonStep related actions.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping("/lessonSteps")
public class LessonStepRestController {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private LessonStepService lessonStepService;

	@Autowired
	private LessonStepResourceAssembler lessonStepResourceAssembler;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * 
	 * Get a lessonStep by its id.
	 * 
	 * @param lessonStepId
	 * @return a LessonStepResource if the lessonStep was found or a 404 status code with "lesson Step not found" message.
	 */
	@RequestMapping(value = "/{lessonStepId}", method = RequestMethod.GET)
	public HttpEntity<LessonStepResource> getLessonStep(//
			@PathVariable Long lessonStepId//
	) {

		LessonStep lessonStep = this.lessonStepService.findById(lessonStepId);

		return new ResponseEntity<>(lessonStepResourceAssembler.toResource(lessonStep), HttpStatus.OK);

	}
}
