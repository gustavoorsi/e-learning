package com.elearning.rest.resources;

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
public class LessonResources {

	private List<LessonResource> lessons;

	private LessonResources() {
	}

	public LessonResources(List<LessonResource> lessons) {
		this.lessons = lessons;
	}

	public List<LessonResource> getLessons() {
		return lessons;
	}
	
	
	
	

}
