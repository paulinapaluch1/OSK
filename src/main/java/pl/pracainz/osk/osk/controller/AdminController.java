package pl.pracainz.osk.osk.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pl.pracainz.osk.osk.dao.UserRepository;

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

	/*@GetMapping("/users")
	public String showUsers(Model theModel) {
		List<User> theUsers = userRepository.findAll();
		theModel.addAttribute("users", theUsers);
		return "users";
	}
*/
	//@GetMapping("/usersForm")
	//public String showFormForUpdate(Model theModel) {
	//	User theStudent = new User();
	//	theModel.addAttribute("user", theStudent);
	//	return "usersForm";
	//}

	//@PostMapping("save")
	//public String saveStudent(@ModelAttribute("user") User theStudent) {
	///	theStudent.setPassword(encoder.encode("paulis"));
	//	theStudent.setId_role(1);
	///	userRepository.save(theStudent);
	//	return "redirect:users/list";
	//}
	@GetMapping("/login")
	public String login() {
	return "login";
}
	

}
