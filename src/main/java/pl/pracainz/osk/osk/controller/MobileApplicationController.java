package pl.pracainz.osk.osk.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

import pl.pracainz.osk.osk.dao.GpsPointRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.GpsPoint;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.json.InstructorJson;
import pl.pracainz.osk.osk.json.LocationJson;
import pl.pracainz.osk.osk.json.Message;

@RestController
@RequestMapping("/mobile")
public class MobileApplicationController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	InstructorRepository instructorRepository;
	@Autowired
	GpsPointRepository gpsRepository;
	
	@GetMapping("login/{username}/{password}")
	public InstructorJson loginToMobileApp(@PathVariable String username, 
			@PathVariable("password") String password) {
		InstructorJson json;
		User user = userRepository.findByUsername(username);
		if (null != user && encoder.matches(password, user.getPassword())
				&& instructorRepository.findByUserId(user.getId())!=null) {
			Instructor instructor = instructorRepository.findByUserId(user.getId());
			json = new InstructorJson("true",instructor.getId(),instructor.getName(),instructor.getSurname());
		} else {
			json = new InstructorJson("false");
		}return json;}
	
	@PostMapping("send/{instructor}")
	public Message getCoordinates(@RequestBody ArrayList<LocationJson> coordinates, 
			@PathVariable("instructor") Integer id) {
	
	//for(LocationJson json : coordinates) {
		
	//}
		//GpsPoint point = new GpsPoint();
	
		gpsRepository.save(new GpsPoint(coordinates.get(1).getNs(),
				coordinates.get(1).getNw(), new Date(),1,1));
		return new Message("true");
	}

	@GetMapping("hello")
	public String sayHello() {
		return "Hello world!";
	}
	

}
