package com.elearning.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.elearning.model.constants.SocialMediaService;
import com.elearning.model.entities.User;
import com.elearning.web.form.RegistrationForm;
import com.elearning.web.service.UserService;
import com.elearning.web.service.exceptions.DuplicateEmailException;
import com.elearning.web.util.SecurityUtil;

@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showRegistrationForm(WebRequest request, Model model) {
		Connection<?> connection = new ProviderSignInUtils().getConnectionFromSession(request);

		RegistrationForm registration = createRegistrationDTO(connection);
		model.addAttribute("user", registration);

		return new ModelAndView("login/signup");
	}

	@RequestMapping(method = RequestMethod.POST)
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

	//@formatter:off
	/**
	 * Add a private createRegistrationDTO() method to the class. This method takes a Connection object as a method parameter and returns a RegistrationForm object. We can implement this method by following these steps:
     * 1) Create a new RegistrationForm object.
     * 2) If the Connection object given as a method parameter is not null, the user is creating a new user account by using social sign in. If this is the case, we have to
     *     2.1) Get a UserProfile object by calling the fetchUserProfile() method of the Connection class. This object contains the user information returned by the SaaS API provider.
     *     2.2) Set the email, first name, and the last name to the form object. We can the get this information by calling the methods of the UserProfile class.
     *     2.3) Get a ConnectionKey object by calling the getKey() method of the Connection class. This object contains id of the used social sign in provider and a provider specific user id.
     *     2.4) Set the sign in provider to the form object by following these steps:
     *         2.4.1) Get the sign in provider by calling the getProviderId() method of the ConnectionKey class.
     *         2.4.2) Transform the String returned by the getProviderId() method to uppercase.
     *         2.4.3) Get the correct value of the SocialMediaService enum by calling its nameOf() method. Pass the sign in provider (in uppercase) as a method parameter (This means that the values of the SocialMediaService enum depends from the sign in provider ids).
     *         2.4.4) Set the returned value to the form object.
     * 3) Return the form object.
	 */
	//@formatter:on
	private RegistrationForm createRegistrationDTO(Connection<?> connection) {
		RegistrationForm dto = new RegistrationForm();

		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			dto.setEmail(socialMediaProfile.getEmail());
			dto.setFirstName(socialMediaProfile.getFirstName());
			dto.setLastName(socialMediaProfile.getLastName());

			ConnectionKey providerKey = connection.getKey();
			dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
		}

		return dto;
	}

}
