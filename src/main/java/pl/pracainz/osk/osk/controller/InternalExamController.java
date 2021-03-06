package pl.pracainz.osk.osk.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.InternalExamRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Category;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Student;

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
		theModel.addAttribute("internalexams", internalExamRepository.findByDeleted(0));
		return "adminViews/adminExams/exams";
	}
	
	@GetMapping("/listArchived")
	public String listArchivedInternalExams(Model theModel) {
		theModel.addAttribute("internalexams", internalExamRepository.findByDeleted(1));
		return "adminViews/adminExams/examsArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("internalexam", new InternalExam());
		return "adminViews/adminExams/examForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_internalExam") int id, Model theModel) {
		theModel.addAttribute("internalexam",  internalExamRepository.findById(id));
		return "adminViews/adminExams/examForm";
	}

	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String saveExam(@ModelAttribute("internalexam") InternalExam theInternalExam, Model theModel) {
		internalExamRepository.save(theInternalExam);
		theModel.addAttribute("saved", true);
		return listInternalExams(theModel) ;
	}
	 
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String validateForm(@Valid @ModelAttribute("internalexam")InternalExam internalexam, BindingResult result,
			Model theModel) {
		if(result.hasErrors()) {
			return "adminViews/adminExams/examForm";
		}
		return saveExam(internalexam, theModel);
	}
	
	@GetMapping("/archiveExam")
	public String archiveExam(@RequestParam("id_internalExam") int id, Model theModel) {
		InternalExam theInternalExam = internalExamRepository.getOne(id);
		theInternalExam.setDeleted(1);
		internalExamRepository.save(theInternalExam);
		return "redirect:/exams/list";
	}
	
	@GetMapping("/unarchiveExam")
	public String unarchiveExam(@RequestParam("id_internalExam") int id, Model theModel) {
		InternalExam theInternalExam = internalExamRepository.getOne(id);
		theInternalExam.setDeleted(0);
		internalExamRepository.save(theInternalExam);
		return "redirect:/exams/listArchived";
	}
	
	@GetMapping("/listPassed")
	public String passedExams(Model theModel) {
		theModel.addAttribute("internalexams", internalExamRepository.findByResult(1));
		return "adminViews/adminExams/exams";
	}
	
	@GetMapping("/listFailed")
	public String failedExams(Model theModel) {
		theModel.addAttribute("internalexams", internalExamRepository.findByResult(0));
		return "adminViews/adminExams/exams";
	}

	@ModelAttribute("students")
	public List<Student> students() {
	    return studentRepository.findByDeleted(0);
	}
	
	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
	    return instructorRepository.findByDeleted(0);
	
}
}
