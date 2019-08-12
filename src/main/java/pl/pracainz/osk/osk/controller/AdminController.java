package pl.pracainz.osk.osk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	
	public AdminController() {
	}
	
	@GetMapping("/")
	public String showAdminProfile() {
	
		return "adminViews/adminProfile";
	}
	
	@GetMapping("/cars")
	public String showCars() {
		
		return "adminViews/adminCars/cars";
	}
	
	@GetMapping("/exams")
	public String showExams() {
		
		return "adminViews/adminExams/exams";
	}
	
	
	

}
