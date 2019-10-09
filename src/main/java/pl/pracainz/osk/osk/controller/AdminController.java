package pl.pracainz.osk.osk.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pracainz.osk.osk.dao.UserRepository;

@Controller
public class AdminController {

	PasswordEncoder encoder;

	public AdminController(UserRepository userRepository, PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	@GetMapping("/")
	public String showAdminProfile() {
		return "adminViews/adminProfile";
	}

	@GetMapping("/login")
	public String login() {
	return "login";
}
	

}
