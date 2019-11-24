package pl.pracainz.osk.osk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pracainz.osk.jsonObject.InstructorJson;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.User;

@RestController
@RequestMapping("/mobile")
public class MobileApplicationController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	InstructorRepository instructorRepository;
	
	@GetMapping("login/{username}/{password}")
	public InstructorJson loginToMobileApp(@PathVariable String username, @PathVariable("password") String password) {

		InstructorJson json;
		User user = userRepository.findByUsername(username);
		if (null != user && encoder.matches(password, user.getPassword())) {

			json = new InstructorJson("true");
			Instructor instructor = instructorRepository.findByUserId(user.getId());
			json.setName(instructor.getName());
			json.setSurname(instructor.getSurname());

		} else {
			json = new InstructorJson("false");

		}
		return json;

	}

	@GetMapping("hello")
	public String sayHello() {
		return "Hello world!";
	}

}
