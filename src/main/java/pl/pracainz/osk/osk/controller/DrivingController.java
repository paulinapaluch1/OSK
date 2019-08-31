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
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Driving;

@Controller
@RequestMapping("/drivings")
public class DrivingController {

	private DrivingRepository drivingRepository;
	private DrivingTypeRepository drivingTypeRepository;
	private StudentRepository studentRepository;

	public DrivingController(DrivingRepository repository, DrivingTypeRepository drivingTypeRepository,
			StudentRepository studentRepository) {
		this.drivingRepository = repository;
		this.drivingTypeRepository = drivingTypeRepository;
		this.studentRepository = studentRepository;
	}

	@GetMapping("/list")
	public String listDrivings(Model theModel) {
		theModel.addAttribute("drivings", drivingRepository.findByDeleted(0));
		return "adminViews/adminDrivings/drivings";
	}

	@GetMapping("/listArchived")
	public String listArchivedDrivings(Model theModel) {
		theModel.addAttribute("drivings", drivingRepository.findByDeleted(1));
		return "adminViews/adminDrivings/drivingsArchived";
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

	@GetMapping("/archiveDriving")
	public String archiveDriving(@RequestParam("id_driving") int id, Model theModel) {

		Driving theDriving = drivingRepository.getOne(id);
		theDriving.setDeleted(1);
		drivingRepository.save(theDriving);

		return "redirect:/drivings/list";
	}

	@GetMapping("/unarchiveDriving")
	public String unarchiveDriving(@RequestParam("id_driving") int id, Model theModel) {

		Driving theDriving = drivingRepository.getOne(id);
		theDriving.setDeleted(0);
		drivingRepository.save(theDriving);

		return "redirect:/drivings/listArchived";
	}

}
