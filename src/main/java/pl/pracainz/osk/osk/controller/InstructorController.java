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
import pl.pracainz.osk.osk.entity.Instructor;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
	private InstructorRepository instructorRepository;
	
	public InstructorController(InstructorRepository repository) {
	this.instructorRepository=repository;
	}
	
	@GetMapping("/list")
	public String listInstructors(Model theModel) {
		List<Instructor> theInstructors=instructorRepository.findByDeleted(0);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructors";
	}
	
	@GetMapping("/listArchived")
	public String listArhivedInstructors(Model theModel) {
		List<Instructor> theInstructors=instructorRepository.findByDeleted(1);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructorsArchived";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Instructor theInstructor = new Instructor();
		theModel.addAttribute("instructor", theInstructor);
		return "adminViews//adminInstructors/instructorForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_instructor") int id,
									Model theModel) {
		Optional<Instructor> theInstructor = instructorRepository.findById(id);
		theModel.addAttribute("instructor", theInstructor);
		return "adminViews/adminInstructors/instructorForm";			
	}
	
	@PostMapping("save")
	public String saveInstructor(@ModelAttribute("instructor") Instructor theInstructor) {
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/list";
	}
	
	@GetMapping("/archiveInstructor")
	public String archiveInstructor(@RequestParam("id_instructor") int id, Model theModel) 
	{
		Instructor theInstructor = instructorRepository.getOne(id);
		theInstructor.setDeleted(1);
		instructorRepository.save(theInstructor);
		
		return "redirect:/instructors/list";
	}

	@GetMapping("/unarchiveInstructor")
	public String unarchiveInstructor(@RequestParam("id_instructor") int id, Model theModel) {
		Instructor theInstructor = instructorRepository.getOne(id);
		theInstructor.setDeleted(0);
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/listArchived";
	}

}
