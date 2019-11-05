package pl.pracainz.osk.osk.controller;

import java.util.List;

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

import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.dao.DrivingTypeRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.DrivingType;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;

@Controller
@RequestMapping("/drivings")
public class DrivingController {

	private DrivingRepository drivingRepository;
	private DrivingTypeRepository drivingTypeRepository;
	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private TimetableRepository timetableRepository;

	public DrivingController(DrivingRepository repository, DrivingTypeRepository drivingTypeRepository,
			StudentRepository studentRepository, InstructorRepository instructorRepository,
			TimetableRepository timetableRepository) {
		this.drivingRepository = repository;
		this.drivingTypeRepository = drivingTypeRepository;
		this.studentRepository = studentRepository;
		this.instructorRepository = instructorRepository;
		this.timetableRepository = timetableRepository;
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
		theModel.addAttribute("driving", new Driving());
		return "adminViews/adminDrivings/drivingForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_driving") int id, Model theModel) {
		theModel.addAttribute("driving", drivingRepository.getOne(id));

		return "adminViews/adminDrivings/drivingForm";
	}

	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String validateForm(@Valid Driving theDriving, BindingResult result, Model theModel) {
		if(result.hasErrors()) {
			return "adminViews/adminDrivings/drivingForm";
		}
		return saveDriving(theDriving, theModel);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.GET)
	public String saveDriving(@ModelAttribute("driving")  Driving theDriving, Model theModel) {
		Driving driving = drivingRepository.getOne(theDriving.getId());
		Timetable timetable = timetableRepository.getOne(driving.getTimetable().getId());
		timetable.setDrivingType(theDriving.getTimetable().getDrivingType());
		timetable.setInstructor(theDriving.getTimetable().getInstructor());
		theDriving.setTimetable(timetable);
		drivingRepository.save(theDriving);
		theModel.addAttribute("saved", true);
		return listDrivings(theModel);
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

	// dla instruktora
	@GetMapping("/doneDriving")
	public String doneDriving(@RequestParam("id_driving") int id, Model theModel) {
		Driving theDriving = drivingRepository.getOne(id);
		theDriving.setDone(1);
		drivingRepository.save(theDriving);
		return "redirect:/instructors/showDrivings";
	}

	@GetMapping("/undoneDriving")
	public String undoneDriving(@RequestParam("id_driving") int id, Model theModel) {
		Driving theDriving = drivingRepository.getOne(id);
		theDriving.setDone(0);
		drivingRepository.save(theDriving);
		return "redirect:/instructors/showDrivings";
	}

	// dla kursanta
	@GetMapping("/cancelDriving")
	public String cancelDriving(@RequestParam("id_driving") int id, Model theModel) {
		Driving theDriving = drivingRepository.getOne(id);
		theDriving.setCancelled(1);
		theDriving.setDone(0);
		drivingRepository.save(theDriving);
		return "redirect:/students/showDrivings";
	}

	@GetMapping("/types")
	public String editDrivingTypes(Model theModel) {
		theModel.addAttribute("drivingTypeToAdd", new DrivingType());
		return "adminViews/adminDrivings/types";
	}

	@PostMapping("/saveEditedDrivingType")
	public String saveEditedDrivingType(@ModelAttribute("drivingType") DrivingType theDrivingType, Model theModel) {
		DrivingType drivingType = drivingTypeRepository.getOne(theDrivingType.getId());
		drivingType.setType(theDrivingType.getType());
		drivingTypeRepository.save(drivingType);
		return "redirect:/drivings/types";
	}

	@PostMapping("/saveNewDrivingType")
	public String saveNewDrivingType(@ModelAttribute("categoryToAdd") DrivingType theDrivingType, Model theModel) {
		drivingTypeRepository.save(theDrivingType);
		return "redirect:/drivings/types";
	}

	@ModelAttribute("drivingType")
	public DrivingType drivingType() {
		return new DrivingType();
	}

	@ModelAttribute("students")
	public List<Student> students() {
		return studentRepository.findByDeleted(0);
	}

	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
		return instructorRepository.findByDeleted(0);
	}

	@ModelAttribute("drivingTypes")
	public List<DrivingType> drivingTypes() {
		return drivingTypeRepository.findAllByOrderByIdAsc();
	}

}
