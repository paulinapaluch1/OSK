package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.InternalExamRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.InternalExam;

@Controller
@RequestMapping("/exams")
public class InternalExamController {

	private InternalExamRepository internalExamRepository; 
	private InstructorRepository instructorRepository;
	private StudentRepository studentRepository;
	
	public InternalExamController(InternalExamRepository exam, InstructorRepository instructor, StudentRepository student) {
		this.internalExamRepository = exam;
		this.instructorRepository = instructor;
		this.studentRepository = student;
	}
	
	@GetMapping("/list")
	public String listInternalExams(Model theModel) {
		
		List<InternalExam> theInternalExams = internalExamRepository.findAll();
		
		theModel.addAttribute("internalexams", theInternalExams);
		theModel.addAttribute("instructors", instructorRepository.findAll() );
		theModel.addAttribute("students", studentRepository.findAll() );
		
		return "adminViews/adminExams/exams";
	}

	
}
