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
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
	private InstructorRepository instructorRepository;

	public InstructorController(InstructorRepository repository) {
		this.instructorRepository = repository;
	}

	@GetMapping("/list")
	public String listInstructors(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findByDeleted(0);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructors";
	}

	@GetMapping("/listArchived")
	public String listArhivedInstructors(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findByDeleted(1);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructorsArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("instructor", new Instructor());
		return "adminViews//adminInstructors/instructorForm";
	}

	@GetMapping("/showFormForUpdate")

	public String showFormForUpdate(@RequestParam("id_instructor") int id,
									Model theModel) {
		theModel.addAttribute("instructor", instructorRepository.findById(id));
		return "adminViews/adminInstructors/instructorForm";			

	}

	@PostMapping("save")
	public String saveInstructor(@ModelAttribute("instructor") Instructor theInstructor) {
		theInstructor.setDeleted(0);
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/list";
	}

	@GetMapping("/archiveInstructor")
	public String archiveInstructor(@RequestParam("id_instructor") int id, Model theModel) {
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

	@GetMapping("/profile")
	public String showProfile(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findAll();
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("instructor", instructorRepository.getOne(3));

		return "instructorViews/instructorProfile";
	}

	@GetMapping("/updateDataInstructor")
	public String instructorDataUpdate(@RequestParam("id_instructor") int id, Model theModel) {
		Optional<Instructor> theInstructor = instructorRepository.findById(id);
		theModel.addAttribute("instructor", theInstructor);
		return "instructorViews/editInstructorProfile";
	}

	@PostMapping("/data/save")
	public String saveDataInstructor(@ModelAttribute("instructor") Instructor theInstructor) {
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/profile";
	}

	@GetMapping("/showCourses")
	public String listCourses(Model theModel) {
		List<Course> theCourses = instructorRepository.queryFindCourses(2);
		theModel.addAttribute("courses", theCourses);
		return "instructorViews/instructorCourses/courses";
	}
	
	@GetMapping("/showExams")
	public String listExams(Model theModel) {
		List<InternalExam> theExams = instructorRepository.queryFindExams(1);
		theModel.addAttribute("internalexams", theExams);
		return "instructorViews/instructorExams/exams";
	}
	
	@GetMapping("/showStudents")
	public String listStudents(Model theModel) {
		List<Student> theStudents = instructorRepository.queryFindStudents(2);
		theModel.addAttribute("students", theStudents);
		return "instructorViews/instructorStudents/students";
	}
	
	@GetMapping("/showOpinions")
	public String listOpinions(Model theModel) {
		List<InstructorOpinion> theOpinions = instructorRepository.queryFindOpinions(1);
		theModel.addAttribute("instructoropinions", theOpinions);
		return "instructorViews/instructorOpinions/opinionsAboutInstructor";
	}
	/*
	@GetMapping("/showDrivings")
	public String listDrivings(Model theModel) {
		List<Driving> theDrivings = instructorRepository.queryFindDrivings(1);
		theModel.addAttribute("drivings", theDrivings);
		return "instructorViews/instructorDrivings/drivings";
	}
*/
}
