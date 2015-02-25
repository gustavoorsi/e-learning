package com.elearning.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Model class that represent a Lesson. A lesson can have as many lessonStep or either none, only requirement is to have a lessonName and
 * belong to a course.
 * 
 * @author Gustavo Orsi
 *
 */
@Entity
public class Lesson {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "lesson_name")
	private String lessonName;

	@OneToMany(mappedBy = "lesson", cascade = { CascadeType.PERSIST })
	private List<LessonStep> lessonSteps;

	@JsonIgnore
	@ManyToOne
	private Course course;

	public Lesson() {
	}

	public Lesson(String lessonName) {
		this.lessonName = lessonName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public List<LessonStep> getLessonSteps() {
		return lessonSteps;
	}

	public void setLessonSteps(List<LessonStep> lessonSteps) {
		this.lessonSteps = lessonSteps;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Lesson addLessonStep(LessonStep lessonStep) {
		if (this.lessonSteps == null) {
			this.lessonSteps = new ArrayList<LessonStep>();
		}

		this.lessonSteps.add(lessonStep);
		lessonStep.setLesson(this);

		return this;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("ID[").append(this.id).append("], course[").append(this.course.getCourseTopic()).append("], lessonName[")
				.append(this.lessonName).append("].").toString();
	}

}
