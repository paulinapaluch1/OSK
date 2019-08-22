package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.CarOpinionRepository;
import pl.pracainz.osk.osk.dao.CarRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.CarOpinion;
import pl.pracainz.osk.osk.entity.Lecture;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/opinions")
public class CarOpinionController {
	private CarOpinionRepository carOpinionRepository;
	private CarRepository carRepository;
	private StudentRepository studentRepository;
	
	public CarOpinionController(CarOpinionRepository repository, CarRepository car, StudentRepository student) {
		this.carOpinionRepository = repository;
		this.carRepository = car;
		this.studentRepository = student;
	}
	
	
	@GetMapping("/list")
	public String listOpinions(Model theModel) {
		List<CarOpinion> theOpinions = carOpinionRepository.findAll();
//		List<Car> theCars = carRepository.findAll();
//		List<Student> theStudents = studentRepository.findAll();
//		
		theModel.addAttribute("caropinions", theOpinions);
//		theModel.addAttribute("cars", theCars);
//		theModel.addAttribute("students", theStudents);
//		
		return "adminViews/adminOpinions/caropinions";
	}

}
