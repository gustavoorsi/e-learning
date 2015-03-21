package com.elearning.model.constants;

public enum Role {
    ROLE_USER("ROLE_USER"), 
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CLIENT("ROLE_CLIENT");
    
    private String name;
    
    private Role( String role ){
    	this.name = role;
    }
    
    public String getName(){
    	return name;
    }
    
}
