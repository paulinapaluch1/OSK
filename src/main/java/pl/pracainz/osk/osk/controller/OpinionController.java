package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	public OpinionController(CarOpinionRepository car, InstructorOpinionRepository instructor, CarRepository carrep,
			StudentRepository studentrep, InstructorRepository instrrep) {
		this.carOpinionRepository = car;
		this.instructorOpinionRepository = instructor;
		this.carRepository = carrep;
		this.studentRepository = studentrep;
		this.instructorRepository = instrrep;
	}

	@GetMapping("/allOpinions")
	public String listOpinions(Model theModel) {
		return "adminViews/adminOpinions/toAllOpinions";
	}

	// ---------------------------------------------------
	// instruktorzy
	// ---------------------------------------------------

	@GetMapping("/instructors")
	public String listInstructorOpinions(Model theModel) {

		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/toOpinionsAboutInstructors";
	}

	@GetMapping("/instructors/newOpinions")
	public String listNewOpinions(Model theModel) {

		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/newOpinionsAboutInstructors";
	}

	@GetMapping("/instructors/checkedOpinions")
	public String listCheckedOpinions(Model theModel) {

		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/confirmedOpinionsAboutInstructors";
	}

	@GetMapping("/instructors/rejectedOpinions")
	public String listRejectedOpinions(Model theModel) {

		List<InstructorOpinion> theInstructorOpinions = instructorOpinionRepository.findAll();
		List<Instructor> theInstructors = instructorRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("instructoropinions", theInstructorOpinions);
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/rejectedOpinionsAboutInstructors";
	}

	@GetMapping("/confirmInstructor")
	public String confirmInstructorOpinion(@RequestParam("id_instructorOpinion") int id, Model theModel) {

		InstructorOpinion theInstructorOpinion = instructorOpinionRepository.getOne(id);
		theInstructorOpinion.setStatus("zatwierdzona");
		instructorOpinionRepository.save(theInstructorOpinion);

		return "redirect:/opinions/instructors/newOpinions";
	}

	@GetMapping("/rejectInstructor")
	public String rejectInstructorOpinion(@RequestParam("id_instructorOpinion") int id, Model theModel) {

		InstructorOpinion theInstructorOpinion = instructorOpinionRepository.getOne(id);
		theInstructorOpinion.setStatus("odrzucona");
		instructorOpinionRepository.save(theInstructorOpinion);

		return "redirect:/opinions/instructors/newOpinions";
	}

	@GetMapping("/cancelInstructor")
	public String cancelInstructorOpinion(@RequestParam("id_instructorOpinion") int id, Model theModel) {

		InstructorOpinion theInstructorOpinion = instructorOpinionRepository.getOne(id);
		theInstructorOpinion.setStatus("nowa");
		instructorOpinionRepository.save(theInstructorOpinion);

		return "redirect:/opinions/instructors";
	}

	// ---------------------------------------------------
	// samochody
	// ---------------------------------------------------

	@GetMapping("/cars")
	public String listCarOpinions(Model theModel) {
		List<CarOpinion> theCarOpinions = carOpinionRepository.findAll();
		List<Car> theCars = carRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("caropinions", theCarOpinions);
		theModel.addAttribute("cars", theCars);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/toOpinionsAboutCars";
	}

	@GetMapping("/cars/newOpinions")
	public String listNewCarOpinions(Model theModel) {

		List<CarOpinion> theCarOpinions = carOpinionRepository.findAll();
		List<Car> theCars = carRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("caropinions", theCarOpinions);
		theModel.addAttribute("cars", theCars);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/newOpinionsAboutCars";
	}

	@GetMapping("/cars/checkedOpinions")
	public String listCheckedCarOpinions(Model theModel) {

		List<CarOpinion> theCarOpinions = carOpinionRepository.findAll();
		List<Car> theCars = carRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("caropinions", theCarOpinions);
		theModel.addAttribute("cars", theCars);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/confirmedOpinionsAboutCars";
	}

	@GetMapping("/cars/rejectedOpinions")
	public String listRejectedCarOpinions(Model theModel) {

		List<CarOpinion> theCarOpinions = carOpinionRepository.findAll();
		List<Car> theCars = carRepository.findAll();
		List<Student> theStudents = studentRepository.findAll();

		theModel.addAttribute("caropinions", theCarOpinions);
		theModel.addAttribute("cars", theCars);
		theModel.addAttribute("students", theStudents);

		return "adminViews/adminOpinions/rejectedOpinionsAboutCars";
	}

	@GetMapping("/confirmCar")
	public String confirmCarOpinion(@RequestParam("id_carOpinion") int id, Model theModel) {

		CarOpinion theCarOpinion = carOpinionRepository.getOne(id);
		theCarOpinion.setStatus("zatwierdzona");
		carOpinionRepository.save(theCarOpinion);

		return "redirect:/opinions/cars/newOpinions";
	}

	@GetMapping("/rejectCar")
	public String rejectCarOpinion(@RequestParam("id_carOpinion") int id, Model theModel) {

		CarOpinion theCarOpinion = carOpinionRepository.getOne(id);
		theCarOpinion.setStatus("odrzucona");
		carOpinionRepository.save(theCarOpinion);

		return "redirect:/opinions/cars/newOpinions";
	}

	@GetMapping("/cancelCar")
	public String cancelCarOpinion(@RequestParam("id_carOpinion") int id, Model theModel) {

		CarOpinion theCarOpinion = carOpinionRepository.getOne(id);
		theCarOpinion.setStatus("nowa");
		carOpinionRepository.save(theCarOpinion);

		return "redirect:/opinions/cars";
	}

}
