package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Lesson;
import com.elearning.model.entities.LessonStep;
import com.elearning.model.exception.EntityNotFoundException;
import com.elearning.model.exception.LessonStepNotFoundException;
import com.elearning.model.persistence.jparepositories.LessonStepRepository;
import com.elearning.service.LessonStepService;

/**
 * 
 * Service layer. All lesson Step related service methods goes here.
 * 
 * @author Gustavo Orsi
 *
 */
@Service( value = "lessonStepService" )
@Transactional
public class LessonStepServiceImpl implements LessonStepService {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	@Autowired
	private LessonStepRepository lessonStepRepository;

	// *************************************************************//
	// ********************* METHOD SERVICES ***********************//
	// *************************************************************//

	/**
	 * 
	 * Find a lessonStep by its id and check if belongs to the lesson, if not found then throw a <code>LessonStepNotFoundException</code>
	 * 
	 */
	@Override
	public LessonStep findByLessonAndId(Lesson lesson, Long lessonStepId) {

		LessonStep lessonStep = this.lessonStepRepository.findByLessonAndId(lesson, lessonStepId).orElseThrow(() -> new EntityNotFoundException(lessonStepId));

		return lessonStep;
	}
	
	/**
	 * Returns a {@link Page} of lessonSteps meeting the paging restriction provided in the {@code Pageable} object.
	 * 
	 * @param pageable
	 * @return a page of lessonStep
	 */
	@Override
	public Page<LessonStep> findAll(Pageable pageable) {
		return this.lessonStepRepository.findAll(pageable);
	}

	@Override
	public LessonStep findById(Long lessonStepId) {
		
		LessonStep lessonStep = this.lessonStepRepository.findById(lessonStepId).orElseThrow(() -> new LessonStepNotFoundException(lessonStepId));
		
		return lessonStep;
	}
	
	

}
