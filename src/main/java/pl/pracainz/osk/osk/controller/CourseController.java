package pl.pracainz.osk.osk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Course theCourse = new Course();
		
		theModel.addAttribute("course", theCourse);
		
		return "adminViews/adminCourses/courseForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_course") int id,
									Model theModel) {
		
		Optional<Course> theCourse = courseRepository.findById(id);
		
		theModel.addAttribute("course", theCourse);
		
		return "adminViews/adminCourses/courseForm";		
	}
	

	@PostMapping("save")
	public String saveCourse(@ModelAttribute("course") Course theCourse) {
		courseRepository.save(theCourse);
		
		return "redirect:/courses/list";
	}
	
	
	
	
	
	
}
