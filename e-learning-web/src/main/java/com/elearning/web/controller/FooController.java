package com.elearning.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.model.entities.Foo;
import com.elearning.service.FooService;

@Controller
@RequestMapping(value = "/foos")
public class FooController {

	@Autowired
	@Qualifier(value = "fooRestService")
	private FooService fooService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getFoos() {

		Map<String, Object> model = new HashMap<String, Object>();

		Page<Foo> foos = fooService.findAll(new PageRequest(0, 10));

		model.put("foos", foos.getContent());

		return new ModelAndView("foos/foos", model);

	}

}
