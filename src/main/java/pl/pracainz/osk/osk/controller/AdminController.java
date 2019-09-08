package pl.pracainz.osk.osk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.User;

@Controller
public class AdminController {

	private UserRepository userRepository;
	PasswordEncoder encoder;

	public AdminController(UserRepository userRepository, PasswordEncoder encoder) {
		this.encoder = encoder;
		this.userRepository = userRepository;
	}

	@GetMapping("/")
	public String showAdminProfile() {
		return "adminViews/adminProfile";
	}

	@GetMapping("/users")
	public String showUsers(Model theModel) {
		List<User> theUsers = userRepository.findAll();
		theModel.addAttribute("users", theUsers);
		return "users";
	}

	@GetMapping("/usersForm")
	public String showFormForUpdate(Model theModel) {
		User theStudent = new User();
		theModel.addAttribute("user", theStudent);
		return "usersForm";
	}

	@PostMapping("save")
	public String saveStudent(@ModelAttribute("user") User theStudent) {
		theStudent.setPassword(encoder.encode("paulis"));
		userRepository.save(theStudent);
		return "redirect:users/list";
	}


}
