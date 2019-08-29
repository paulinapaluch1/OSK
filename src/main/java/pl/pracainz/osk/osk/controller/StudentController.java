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

import pl.pracainz.osk.osk.dao.InstructorOpinionRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.Lecture;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/students")
public class StudentController {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private InstructorOpinionRepository instructorOpinionRepository;

	public StudentController(StudentRepository repository, InstructorRepository instructor, InstructorOpinionRepository instructorOpinion) {
		this.studentRepository = repository;
		this.instructorRepository = instructor;
		this.instructorOpinionRepository = instructorOpinion;
	}

	@GetMapping("/list")
	public String listStudents(Model theModel) {

		List<Student> theStudents = studentRepository.findAll();

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
	public String showFormForUpdate(@RequestParam("id_student") int id, Model theModel) {

		Optional<Student> theStudent = studentRepository.findById(id);
		theModel.addAttribute("student", theStudent);

		return "adminViews/adminStudents/addStudent";
	}

	@PostMapping("save")
	public String saveStudent(@ModelAttribute("student") Student theStudent) {
		studentRepository.save(theStudent);

		return "redirect:/students/list";
	}

	@GetMapping("/profile")
	public String showProfile(Model theModel) {
		List<Student> theStudents = studentRepository.findAll();
		theModel.addAttribute("students", theStudents);
		theModel.addAttribute("student", studentRepository.getOne(3));

		return "studentViews/studentProfile";
	}

	@GetMapping("/updateDataStudent")
	public String studentDataUpdate(@RequestParam("id_student") int id, Model theModel) {

		Optional<Student> theStudent = studentRepository.findById(id);
		theModel.addAttribute("student", theStudent);

		return "studentViews/editStudentProfile";
	}

	@PostMapping("/data/save")
	public String saveDataStudent(@ModelAttribute("student") Student theStudent) {

		Student student = studentRepository.getOne(theStudent.getId());
		student.setLogin(theStudent.getLogin());
		student.setName(theStudent.getName());
		student.setSurname(theStudent.getSurname());
		student.setPKK(theStudent.getPKK());
		student.setDeleted(theStudent.getDeleted());

		studentRepository.save(student);

		return "redirect:/students/profile";
	}

	@GetMapping("/showInstructors")
	public String listInstructors(Model theModel) {

		List<Instructor> theInstructors = instructorRepository.findAll();

		theModel.addAttribute("instructors", theInstructors);

		return "studentViews/studentInstructors/instructors";
	}
	
	
	@GetMapping("/rateInstructors")
	public String rateInstructors(@RequestParam("id_instructor") int id, Model theModel) {

		Instructor theInstructor = new Instructor();
		theModel.addAttribute("instructor", theInstructor);
		theModel.addAttribute("instructoropinions", instructorOpinionRepository.findAll());
		
//		Optional<InstructorOpinion> theInstructorOpinion = instructorOpinionRepository.findById(id);
//		theModel.addAttribute("instructoropinion", theInstructorOpinion);
//		theModel.addAttribute("instructors", instructorRepository.findAll());
		
		return "studentViews/studentInstructors/rateInstructors";
	}
	
	@PostMapping("saveInstructorOpinion")
	public String saveInstructorOpinion(@ModelAttribute("instructoropinion") InstructorOpinion theInstructorOpinion) {
		instructorOpinionRepository.save(theInstructorOpinion);

		return "redirect:/students/showInstructors";
	}

}
