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

private CourseRepository courseRepository;
	
	public CourseController(CourseRepository repository) {
	   this.courseRepository = repository;
	}
	

	
	@GetMapping("/list")
	public String listCourses(Model theModel) {
		
		List<Course> theCourses= courseRepository.findAll();
		
		theModel.addAttribute("courses", theCourses);
		
		return "adminViews/adminCourses/courses";
	}
	
}
