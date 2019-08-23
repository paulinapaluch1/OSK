package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.CarOpinionRepository;
import pl.pracainz.osk.osk.dao.CarRepository;
import pl.pracainz.osk.osk.dao.InstructorOpinionRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.CarOpinion;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/opinions")
public class OpinionController {
	private CarOpinionRepository carOpinionRepository;
	private InstructorOpinionRepository instructorOpinionRepository;
	
	private CarRepository carRepository;
	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	
	public OpinionController(CarOpinionRepository car, InstructorOpinionRepository instructor, CarRepository carrep, StudentRepository studentrep, InstructorRepository instrrep) {
		this.carOpinionRepository = car;
		this.instructorOpinionRepository = instructor;
		this.carRepository = carrep;
		this.studentRepository = studentrep;
		this.instructorRepository = instrrep;
	}
	
	@GetMapping("/instructors/newOpinions")
	public String listNewOpinions(Model theModel) {
		
		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);
		
		
		return "adminViews/adminOpinions/newInstructor";
	}
	
	@GetMapping("/instructors/checkedOpinions")
	public String listCheckedOpinions(Model theModel) {
		
		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);
		
		
		return "adminViews/adminOpinions/checkedInstructor";
	}
	
	@GetMapping("/instructors/rejectedOpinions")
	public String listRejectedOpinions(Model theModel) {
		
		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);
		
		
		return "adminViews/adminOpinions/rejectedInstructor";
	}
	
	@GetMapping("/allOpinions")
	public String listOpinions(Model theModel) {
		return "adminViews/adminOpinions/opinions";
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
	
	
	
	@GetMapping("/cars")
	public String listCarOpinions(Model theModel) {
		List<CarOpinion> theCarOpinions = carOpinionRepository.findAll();
		List<Car> theCars = carRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();
		
		theModel.addAttribute("caropinions", theCarOpinions);
		theModel.addAttribute("cars", theCars);
		theModel.addAttribute("students", theStudents);
		
		return "adminViews/adminOpinions/caropinions";
	}
	
	
}
