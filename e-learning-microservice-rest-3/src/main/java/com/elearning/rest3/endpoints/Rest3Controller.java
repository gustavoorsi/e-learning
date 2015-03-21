package com.elearning.rest3.endpoints;

import static com.elearning.constants.ElearningConstants.API_SERVICE_3.GET_PRODUCT;
import static com.elearning.constants.ElearningConstants.API_SERVICE_3.PRODUCT_ENDPOINT_BASE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.rest3.domain.Product;
import com.elearning.rest3.repository.ProductRepository;
import com.elearning.rest3.resources.ProductResource;
import com.elearning.rest3.resources.assemblers.ProductResourceAssembler;

/**
 * 
 * Rest endpoint.
 * 
 * @author Gustavo Orsi
 *
 */
@RestController
@RequestMapping(PRODUCT_ENDPOINT_BASE)
public class Rest3Controller {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductResourceAssembler productResourceAssembler;

	// *************************************************************//
	// ********************* REST SERVICES *************************//
	// *************************************************************//

	/**
	 * Return a product loaded from apache solr.
	 * 
	 * @return
	 */
	@RequestMapping(value = GET_PRODUCT, method = RequestMethod.GET)
	public HttpEntity<ProductResource> getProduct( @PathVariable String productId ) {

		Product product = this.productRepository.findOne( productId );

		return new ResponseEntity<ProductResource>(this.productResourceAssembler.toResource(product), HttpStatus.OK);
	}
	
	
	
	/**
	 * Return a product loaded from apache solr.
	 * 
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST)
	public HttpEntity<?> postProduct( @RequestBody Product input ) {
		
		input = this.productRepository.save( input );

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(linkTo(methodOn(Rest3Controller.class, input.getId()).getProduct(input.getId())).toUri());

		return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
	}

}
