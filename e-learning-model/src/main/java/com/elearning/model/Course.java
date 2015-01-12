package com.elearning.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Model class that represent a Course. A course can have as many lessons or either none, only requirement is to have a courseTopic.
 * 
 * @author Gustavo Orsi
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Course {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String courseTopic;

	@OneToMany(mappedBy = "course", cascade = { CascadeType.PERSIST })
	private List<Lesson> lessons;

	public Course() {
	}

	public Course(final String courseTopic) {
		this.courseTopic = courseTopic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseTopic() {
		return courseTopic;
	}

	public void setCourseTopic(String courseTopic) {
		this.courseTopic = courseTopic;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public Course addLesson(Lesson lesson) {
		if (this.lessons == null) {
			this.lessons = new ArrayList<Lesson>();
		}

		this.lessons.add(lesson);
		lesson.setCourse(this);

		return this;
	}

}
