package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.CourseRepository;
import pl.pracainz.osk.osk.entity.Course;

@Controller
@RequestMapping("/courses")
public class CourseController {

private CourseRepository courseRepo;
	
	public CourseController(CourseRepository service) {
	   this.courseRepo=service;
	}
	
	
	public CourseController() {
	}

	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		List<Course> courses=courseRepo.findAll();
		
		theModel.addAttribute("courses", courses);
		
		return "adminViews/adminCourses/courses";
	}
	
	
	
}
