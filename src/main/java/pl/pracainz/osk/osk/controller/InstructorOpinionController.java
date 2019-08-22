package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.InstructorOpinionRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/opinions")
public class InstructorOpinionController {

	private InstructorOpinionRepository instructorOpinionRepository;
	private InstructorRepository instructorRepository;
	private StudentRepository studentRepository;
	
	public InstructorOpinionController(InstructorOpinionRepository instructoropinions, InstructorRepository instructor, StudentRepository student) {
		this.instructorOpinionRepository = instructoropinions;
		this.instructorRepository = instructor;
		this.studentRepository = student;
	}

	@GetMapping("/instructors")
	public String listInstructorOpinions(Model theModel) {

		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);
		

		return "adminViews/adminOpinions/iopinions";
	}
	/*
	@PostMapping("save")
	public String saveInstructorOpinion(@ModelAttribute("instructorOpinion") InstructorOpinion theInstructorOpinion) {
		instructorOpinionRepository.save(theInstructorOpinion);

		return "redirect:/opinions/instructors";
	}
	
	@PostMapping("undo")
	public String undoInstructorOpinion(@ModelAttribute("instructorOpinion") InstructorOpinion theInstructorOpinion) {
		instructorOpinionRepository.undo(theInstructorOpinion);

		return "redirect:/opinions/instructors";
	}
	
	@PostMapping("delete")
	public String deleteInstructorOpinion(@ModelAttribute("instructorOpinion") InstructorOpinion theInstructorOpinion) {
		instructorOpinionRepository.delete(theInstructorOpinion);

		return "redirect:/opinions/instructors";
	}
	*/
}

