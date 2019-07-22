package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	private StudentRepository studentRepository;
	
	public StudentController(StudentRepository repository) {
	this.studentRepository=repository;
	}
	
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		List<Student> theStudents=studentRepository.findAll();
		
		theModel.addAttribute("students", theStudents);
		
		return "adminViews/adminStudents/students";
	}
	

}
