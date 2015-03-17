package com.elearning.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.elearning.model.entities.Foo;
import com.elearning.model.exception.CourseNotFoundException;
import com.elearning.model.persistence.jparepositories.FooRepository;
import com.elearning.service.FooService;

@Service(value = "fooService")
@Transactional
public class FooServiceImpl implements FooService {

	// *************************************************************//
	// *********************** PROPERTIES **************************//
	// *************************************************************//

	private final FooRepository fooRepository;

	// *************************************************************//
	// ********************* CONSTRUCTORS **************************//
	// *************************************************************//
	@Autowired
	public FooServiceImpl(final FooRepository fooRepository) {
		this.fooRepository = fooRepository;
	}

	@Override
	public Page<Foo> findAll(Pageable pageable) {
		return this.fooRepository.findAll(pageable);
	}

	@Override
	public Foo findById(Long fooId) {

		// note: fooRepository returns an Option instance, so we can use the utility method .orElseThrow() and lambda expression.
		Foo foo = this.fooRepository.findById(fooId).orElseThrow(() -> new CourseNotFoundException(fooId));

		return foo;
	}

}
