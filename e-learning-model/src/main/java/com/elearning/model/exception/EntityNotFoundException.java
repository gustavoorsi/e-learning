package com.elearning.model.exception;

@SuppressWarnings("serial")
public class EntityNotFoundException extends RuntimeException {
	
	public EntityNotFoundException(Long id) {
        super("Entity not found for id '" + id + "'.");
    }
	
	public EntityNotFoundException( String message ){
		super( message );
	}

}
