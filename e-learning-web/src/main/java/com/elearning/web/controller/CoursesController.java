package com.elearning.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.model.entities.Course;
import com.elearning.model.entities.User;
import com.elearning.service.CourseService;
import com.elearning.web.config.security.LoggedInUser;

@Controller
@RequestMapping(value = "/courses")
public class CoursesController {

	@Autowired
	 @Qualifier(value = "courseServiceRest") // use a service that will invoke the rest service to manipulate courses.
//	@Qualifier(value = "courseServiceLocal")
	private CourseService courseService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getCourses( @LoggedInUser User loggedInUser ) {

		Map<String, Object> model = new HashMap<String, Object>();

		List<Course> courses = courseService.findAll();

		model.put("courses", courses);
		model.put("course", new Course());

		return new ModelAndView("courses/courses", model);

	}

	@RequestMapping(method = RequestMethod.POST, params = "add")
	public String addCourse(Model model, @Valid @ModelAttribute final Course course, final BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "courses/courses";
		}

		courseService.addCourse(course);

		return "redirect:/courses";
	}

}
