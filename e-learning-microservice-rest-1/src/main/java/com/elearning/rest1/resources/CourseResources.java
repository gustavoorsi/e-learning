package com.elearning.rest1.resources;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * (Crappy solution for jaxb marshalling and unmarshalling List instances.)
 * Wrapper class needed by jaxb to marshal/unmarshal Lists.
 * 
 * @author Gustavo Orsi
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseResources {

	private List<CourseResource> courses;

	private CourseResources() {
	}

	public CourseResources(List<CourseResource> courses) {
		this.courses = courses;
	}

	public List<CourseResource> getCourses() {
		return courses;
	}
	
	
	
	

}
