package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.service.StudentService;

@Controller
@RequestMapping("/studentss")
public class StudentController {

	private StudentService studentService;
	
	public StudentController(StudentService service) {
	this.studentService=service;
	}
	
	
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		List<Student> theStudents=studentService.findAll();
		
		theModel.addAttribute("students", theStudents);
		
		return "adminViews/adminStudents/students";
	}
	

}
