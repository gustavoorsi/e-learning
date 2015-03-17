package com.elearning.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.elearning.model.entities.Foo;

public interface FooService {

	Page<Foo> findAll(Pageable pageable);

	Foo findById(Long fooId);

}
