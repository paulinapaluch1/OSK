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

import pl.pracainz.osk.osk.dao.CarRepository;
import pl.pracainz.osk.osk.entity.Car;

@Controller
@RequestMapping("/cars")
public class CarController {
	private CarRepository carRepository;

	public CarController(CarRepository repository) {
		this.carRepository = repository;
	}

	@GetMapping("/list")
	public String listCars(Model theModel) {

		List<Car> theCars = carRepository.findAll();

		theModel.addAttribute("cars", theCars);

		return "adminViews/adminCars/cars";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Car theCar = new Car();

		theModel.addAttribute("car", theCar);

		return "adminViews/adminCars/carForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_car") int id, Model theModel) {

		Optional<Car> theCar = carRepository.findById(id);

		theModel.addAttribute("car", theCar);

		return "adminViews/adminCars/carForm";
	}

	@PostMapping("save")
	public String saveCourse(@ModelAttribute("car") Car theCar) {
		carRepository.save(theCar);

		return "redirect:/cars/list";
	}
}