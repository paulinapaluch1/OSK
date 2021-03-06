package pl.pracainz.osk.osk.controller;

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
		theModel.addAttribute("cars", carRepository.findByDeleted(0));
		return "adminViews/adminCars/cars";
	}

	@GetMapping("/listArchived")
	public String listArchivedCars(Model theModel) {
		theModel.addAttribute("cars", carRepository.findByDeleted(1));
		return "adminViews/adminCars/carsArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("car", new Car());
		return "adminViews/adminCars/carForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_car") int id, Model theModel) {
		theModel.addAttribute("car", carRepository.findById(id));
		return "adminViews/adminCars/carForm";
	}

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String validateForm(@Valid Car car, BindingResult result, Model theModel) {
		if(result.hasErrors()) {
			return "adminViews/adminCars/carForm";
		}
		return saveCar(car, theModel);
	}
	
	
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String saveCar(@ModelAttribute("car") Car theCar, Model theModel) {
		theCar.setDeleted(0);
		theModel.addAttribute("saved", true);
		carRepository.save(theCar);
		return listCars(theModel) ;
	}

	@GetMapping("/archiveCar")
	public String archiveCar(@RequestParam("id_car") int id, Model theModel) {
		Car theCar = carRepository.getOne(id);
		theCar.setDeleted(1);
		carRepository.save(theCar);
		return "redirect:/cars/list";
	}

	@GetMapping("/unarchiveCar")
	public String unarchiveCar(@RequestParam("id_car") int id, Model theModel) {
		Car theCar = carRepository.getOne(id);
		theCar.setDeleted(0);
		carRepository.save(theCar);
		return "redirect:/cars/listArchived";
	}
}
