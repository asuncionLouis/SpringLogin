package com.spring.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.model.User;
import com.spring.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password, Model model) {

		List<User> findUser = userRepo.findByUsername(username);

		try {
			User user = findUser.get(0);
			if(isUserAuthenticated(username,user.getPassword(), password)) {
				return "home";
			}
			model.addAttribute("error", "Invalid username/password!");
			return "index";
		} catch (Exception e) {
			model.addAttribute("error", "Invalid username/password!");
			return "index";
		}
	}

	public boolean isUserAuthenticated(String username, String password1, String password2) {
		String authString = "Basic " + password1;
		String[] authParts = authString.split("\\s+");
		String authinfo = authParts[1];
		byte[] bytes = Base64.getDecoder().decode(authinfo);
		String decodedAuth = new String(bytes);
		String[] credentials = decodedAuth.split(":");
		if (credentials[0].equals(username) && credentials[1].equals(password2)) {
			return true;
		}
		return false;
	}

}
