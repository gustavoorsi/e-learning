package com.elearning.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class LessonStep {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String content;
	
	@XmlTransient
	@JsonIgnore
	@ManyToOne
	private Lesson lesson;

	public LessonStep() {
	}

	public LessonStep(String content) {
		this.content = content;
	}

	public LessonStep(String content, Lesson lesson) {
		this.content = content;
		this.lesson = lesson;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Lesson getLesson() {
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("ID[").append(this.id).append("], lesson[").append(this.lesson.getLessonName())
				.append("], content[").append(this.content).append("].").toString();
	}

}
