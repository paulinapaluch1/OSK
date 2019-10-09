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
import pl.pracainz.osk.osk.entity.Category;
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
		InternalExam theInternalExam = new InternalExam();
		theModel.addAttribute("internalexam", theInternalExam);
		return "adminViews/adminExams/examForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_internalExam") int id, Model theModel) {
		Optional<InternalExam> theInternalExam = internalExamRepository.findById(id);
		theModel.addAttribute("internalexam", theInternalExam);
		return "adminViews/adminExams/examForm";
	}

	@PostMapping("save")
	public String saveExam(@ModelAttribute("internalexam") InternalExam theInternalExam) {
		internalExamRepository.save(theInternalExam);
		return "redirect:/exams/list";
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
