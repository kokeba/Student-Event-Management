package com.mum.eventmanagement.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mum.eventmanagement.model.Role;
import com.mum.eventmanagement.model.User;

@RequestMapping("/user")
@Controller
public class UserSignupController {
	@Autowired
	UserSignupService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpForm(User user, Model model) {
		return "/user/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(@ModelAttribute @Valid User user, 
			BindingResult result, HttpServletRequest request)
			throws ServletException {
		if (result.hasErrors()) {
			return "/user/signup";
		}
		user.setRole(Role.ROLE_ADMIN);
		userService.save(user);
		request.login(user.getUsername(), user.getPassword());		
		return "/event/events";
	}

}
