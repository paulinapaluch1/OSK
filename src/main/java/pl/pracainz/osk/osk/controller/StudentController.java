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
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Participant;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;

@Controller
@RequestMapping("/students")
public class StudentController {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private InstructorOpinionRepository instructorOpinionRepository;

	public StudentController(StudentRepository repository, InstructorRepository instructor,
			InstructorOpinionRepository instructorOpinion) {
		this.studentRepository = repository;
		this.instructorRepository = instructor;
		this.instructorOpinionRepository = instructorOpinion;
	}

	@GetMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents = studentRepository.findByDeleted(0);
		theModel.addAttribute("students", theStudents);
		return "adminViews/adminStudents/students";
	}

	@GetMapping("/listArchived")
	public String listArchivedStudents(Model theModel) {
		List<Student> theStudents = studentRepository.findByDeleted(1);
		theModel.addAttribute("students", theStudents);
		return "adminViews/adminStudents/studentsArchived";
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
		theStudent.setDeleted(0);
		studentRepository.save(theStudent);
		return "redirect:/students/list";
	}

	@GetMapping("/archiveStudent")
	public String archiveStudent(@RequestParam("id_student") int id, Model theModel) {

		Student theStudent = studentRepository.getOne(id);
		theStudent.setDeleted(1);
		studentRepository.save(theStudent);
		return "redirect:/students/list";
	}
	
	@GetMapping("/unarchiveStudent")
	public String unarchiveStudent(@RequestParam("id_student") int id, Model theModel) {

		Student theStudent = studentRepository.getOne(id);
		theStudent.setDeleted(0);
		studentRepository.save(theStudent);
		return "redirect:/students/listArchived";
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
		studentRepository.save(theStudent);
		return "redirect:/students/profile";
	}

	@GetMapping("/showInstructors")
	public String listInstructors(Model theModel) {
		//List<Instructor> theInstructors = instructorRepository.findAll();
		//theModel.addAttribute("instructors", theInstructors);
		List<Instructor> theInstructors = studentRepository.queryFindInstructors(2);
		//theModel.addAttribute("instructors", theInstructors);4
		//List<Participant> theInstructors = studentRepository.queryFindCourses();
		theModel.addAttribute("instructors", theInstructors);
		return "studentViews/studentInstructors/instructors";
	}
	
	@GetMapping("/showCars")
	public String listCars(Model theModel) {
		List<Car> theCars = studentRepository.queryFindCars(2);
		theModel.addAttribute("cars", theCars);
		return "studentViews/studentCars/cars";
	}
	
	@GetMapping("/showDrivings")
	public String listDrivings(Model theModel) {
		List<Driving> theDrivings = studentRepository.queryFindDrivings(1);
		theModel.addAttribute("drivings", theDrivings);
		return "studentViews/studentDrivings/drivings";
	}
	
	@GetMapping("/showTimetable")
	public String Timetable(Model theModel) {
		List<Timetable> theTimetable = studentRepository.queryFindTimetable(1);
		theModel.addAttribute("timetable", theTimetable);
		return "studentViews/studentTimetable/timetable";
	}
	
	
	/*
	@GetMapping("/showCourses")
	public String listCourses(Model theModel) {
		List<Course> theCourses = studentRepository.queryFindCourses(1);
		theModel.addAttribute("courses", theCourses);
		return "studentViews/studentCourses/courses";
	}
*/
	
	@GetMapping("/showExams")
	public String listExams(Model theModel) {
		List<InternalExam> theExams = studentRepository.queryFindExams(2);
		theModel.addAttribute("internalexams", theExams);
		
		return"studentViews/studentExams/exams";
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
