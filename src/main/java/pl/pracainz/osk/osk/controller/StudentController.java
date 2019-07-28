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
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Student theStudent = new Student();
		
		theModel.addAttribute("student", theStudent);
		
		return "adminViews/adminStudents/addStudent";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_student") int id,
									Model theModel) {
		
		Optional<Student> theStudent = studentRepository.findById(id);
		
		theModel.addAttribute("student", theStudent);
		
		return "adminViews/adminStudents/addStudent";			
	}
	

	@PostMapping("save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		studentRepository.save(theStudent);
		
		return "redirect:/students/list";
	}
	

}
