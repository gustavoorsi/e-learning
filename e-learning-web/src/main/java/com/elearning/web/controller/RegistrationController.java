package com.elearning.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.elearning.model.entities.User;
import com.elearning.web.form.RegistrationForm;
import com.elearning.web.service.UserService;
import com.elearning.web.service.exceptions.DuplicateEmailException;
import com.elearning.web.util.SecurityUtil;

@Controller
@RequestMapping(value = "/user")
public class RegistrationController {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData, BindingResult result, WebRequest request)
			throws DuplicateEmailException {
		if (result.hasErrors()) {
			return "login/signup";
		}

		User registered = createUserAccount(userAccountData, result);

		if (registered == null) {
			return "login/signup";
		}
		SecurityUtil.logInUser(registered);
		new ProviderSignInUtils().doPostSignUp(registered.getEmail(), request);

		return "redirect:/";
	}

	private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
		User registered = null;

		try {
			registered = service.registerNewUserAccount(userAccountData);
		} catch (DuplicateEmailException ex) {
			addFieldError("user", "email", userAccountData.getEmail(), "NotExist.user.email", result);
		}

		return registered;
	}

	private void addFieldError(String objectName, String fieldName, String fieldValue, String errorCode, BindingResult result) {
		FieldError error = new FieldError(objectName, fieldName, fieldValue, false, new String[] { errorCode }, new Object[] {}, errorCode);

		result.addError(error);
	}

}
