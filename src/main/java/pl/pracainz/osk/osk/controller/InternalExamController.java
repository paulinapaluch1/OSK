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

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		InternalExam theInternalExam = new InternalExam();

		theModel.addAttribute("internalexam", theInternalExam);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("students", studentRepository.findAll());


		return "adminViews/adminExams/examForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_internalExam") int id, Model theModel) {

		Optional<InternalExam> theInternalExam = internalExamRepository.findById(id);
		theModel.addAttribute("internalexam", theInternalExam);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("students", studentRepository.findAll());

		
		return "adminViews/adminExams/examForm";
	}

	@PostMapping("save")
	public String saveLecture(@ModelAttribute("internalexam") InternalExam theInternalExam) {
		internalExamRepository.save(theInternalExam);

		return "redirect:/exams/list";
	}
	
}
