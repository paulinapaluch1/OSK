package pl.pracainz.osk.osk.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.dao.DrivingTypeRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Driving;

@Controller
@RequestMapping("/drivings")
public class DrivingController {

	private DrivingRepository drivingRepository;
	private InstructorRepository instructorRepository;
	private DrivingTypeRepository drivingTypeRepository;
	private StudentRepository studentRepository;

	public DrivingController(DrivingRepository repository, InstructorRepository instructorRepository
					,DrivingTypeRepository drivingTypeRepository,
					 StudentRepository studentRepository)
	 {
		this.drivingRepository = repository;
		this.instructorRepository = instructorRepository;
		 this.drivingTypeRepository = drivingTypeRepository;
		 this.studentRepository = studentRepository;
	}

	@GetMapping("/list")
	public String listDrivings(Model theModel) {
		theModel.addAttribute("drivings", drivingRepository.findAll());
		return "adminViews/adminDrivings/drivings";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Driving theDriving = new Driving();
		theModel.addAttribute("driving", theDriving);
		theModel.addAttribute("drivingTypes", drivingTypeRepository.findAll());
		theModel.addAttribute("students", studentRepository.findAll());

		return "adminViews/adminDrivings/drivingForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_driving") int id, Model theModel) {

		Optional<Driving> theDriving = drivingRepository.findById(id);
		theModel.addAttribute("driving", theDriving);
		theModel.addAttribute("drivingTypes", drivingTypeRepository.findAll());
		theModel.addAttribute("students", studentRepository.findAll());

		return "adminViews/adminDrivings/drivingForm";
	}

	@PostMapping("save")
	public String saveDriving(@ModelAttribute("driving") Driving theDriving) {
		drivingRepository.save(theDriving);

		return "redirect:/drivings/list";
	}

}
