package com.elearning.web.form;

import org.hibernate.validator.constraints.NotBlank;

public class CourseForm {

	@NotBlank
	private String courseTopic;

	public String getCourseTopic() {
		return courseTopic;
	}

	public void setCourseTopic(String courseTopic) {
		this.courseTopic = courseTopic;
	}

}
