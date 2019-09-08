package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.CarRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.entity.Timetable;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	private TimetableRepository timetableRepository;
	private CarRepository carRepository;
	private InstructorRepository instructorRepository;

	public TimetableController(TimetableRepository repository, CarRepository carRepository,
			InstructorRepository instructorRepository) {
		this.timetableRepository = repository;
		this.carRepository = carRepository;
		this.instructorRepository = instructorRepository;
	}

	@GetMapping("/list")
	public String showTimetable(Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		theModel.addAttribute("cars", carRepository.findAll());
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));
		return "adminViews/adminTimetable/timetable";
	}

	@RequestMapping("/earlierDate")
	public String changeDateForEarlier(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		theModel.addAttribute("cars", carRepository.findAll());
		LocalDate yesterday = date.minusDays(1);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));

		return "adminViews/adminTimetable/timetable";
	}

	@RequestMapping("/nextDate")
	public String changeDateForLater(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		theModel.addAttribute("cars", carRepository.findAll());
		LocalDate yesterday = date.plusDays(1);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		return "adminViews/adminTimetable/timetable";
	}

	@RequestMapping(value = "/changeDate", method = RequestMethod.GET)
	public String changeDate(@ModelAttribute("date") @DateTimeFormat(iso = ISO.DATE_TIME) String date,
			BindingResult result, Model theModel) {
		
		theModel.addAttribute("timetables", timetableRepository.findAll());
		theModel.addAttribute("cars", carRepository.findAll());
		LocalDate yesterday = LocalDate.parse(date);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		return "adminViews/adminTimetable/timetable";
	}

	@GetMapping("/edit")
	public String showFormForUpdate(@RequestParam("id_timetable") int id, Model theModel) {
		Optional<Timetable> theTimetable = timetableRepository.findById(id);
		Timetable thisTimetable = timetableRepository.getOne(id);

		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", thisTimetable);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		return "adminViews/adminTimetable/timetableForm";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String changeInstructor(@ModelAttribute("timetable") Timetable theTimetable,Model theModel) 
	{	Timetable timetable = timetableRepository.getOne(theTimetable.getId());
		timetable.setInstructor(theTimetable.getInstructor());
		timetableRepository.save(timetable);
		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", timetable);
		theModel.addAttribute("instructors", instructorRepository.findAll());		
		return "adminViews/adminTimetable/timetableForm";
	}

	
	@RequestMapping(value = "/changeHours", method = RequestMethod.GET)
	public String changeHours(@RequestParam(value = "hour", required = false) String hour,
			@RequestParam(value = "hourBegin", required = false) String hourBegin,
			@ModelAttribute("timetable") Timetable theTimetable, BindingResult result, Model theModel) 
	{	
		Timetable timetable = timetableRepository.getOne(theTimetable.getId());
		int begin = Integer.parseInt(hour);
		int end = begin+2;
		LocalDateTime beginOfPlannedDriving = LocalDateTime.of(timetable.getBegin().getYear(),timetable.getBegin().getMonth(),
		timetable.getBegin().getDayOfMonth(),begin,0,0);
		LocalDateTime endOfPlannedDriving = LocalDateTime.of(timetable.getEnd().getYear(),timetable.getEnd().getMonth(),
		timetable.getEnd().getDayOfMonth(),end,0,0);
		timetable.setBegin(beginOfPlannedDriving);
		timetable.setEnd(endOfPlannedDriving);
		timetableRepository.save(timetable);
		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", timetable);
		theModel.addAttribute("instructors", instructorRepository.findAll());		
		return "adminViews/adminTimetable/timetableForm";
	}

	
	@GetMapping("/editDayForCar")
	public String editDayForCar(@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam("id") int id_car,			Model theModel) {

		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", 
				timetableRepository.queryByDayAndMonthAndYearAndCar(date.getDayOfMonth(),date.getMonth().getValue(),date.getYear(),id_car));	
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("car",carRepository.getOne(id_car));
		theModel.addAttribute("today",date);
		Timetable timetable=new Timetable();
		timetable.setCar(carRepository.getOne(id_car));
		theModel.addAttribute("timetable",timetable);
		theModel.addAttribute("timetableToAdd",new Timetable());
		
		
		
		return "adminViews/adminTimetable/editDayForCar";
	}
	
	
	public String getDayName(LocalDate date) {
		int dayNumber = date.getDayOfWeek().getValue();
		switch (dayNumber) {
		case 1:
			return "Poniedziałek ";
		case 2:
			return "Wtorek ";
		case 3:
			return "Środa ";
		case 4:
			return "Czwartek ";
		case 5:
			return "Piątek ";
		case 6:
			return "Sobota ";
		case 7:
			return "Niedziela ";
		default:
			return "Dzisiaj ";

		}

	}
	

	@RequestMapping(value = "/saveNewTimetableForDay", method = RequestMethod.GET)
	public String saveNewTimetable(@RequestParam(value = "hour", required = false) String hour,
			@ModelAttribute("timetable") Timetable timetable, BindingResult result, Model theModel,
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date) 
	{	Timetable theTimetable = timetableRepository.getOne(timetable.getId());
		int begin = Integer.parseInt(hour);
		int end = begin+2;
		LocalDate day=date;
		theTimetable.setBegin(LocalDateTime.of(theTimetable.getBegin().getYear(),theTimetable.getBegin().getMonth(),theTimetable.getBegin().getDayOfMonth(),begin,0,0));
		theTimetable.setEnd(LocalDateTime.of(theTimetable.getBegin().getYear(),theTimetable.getBegin().getMonth(),theTimetable.getBegin().getDayOfMonth(),end,0,0));
		theTimetable.setInstructor(timetable.getInstructor());
		timetableRepository.save(theTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", //timetableRepository.findAll());		
		timetableRepository.queryByDayAndMonthAndYearAndCar(theTimetable.getBegin().getDayOfMonth(),theTimetable.getBegin().getMonthValue(),theTimetable.getBegin().getYear(),1));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("car",carRepository.getOne(1));
		theModel.addAttribute("today",LocalDate.now());
		Timetable timetable1=new Timetable();
		timetable1.setCar(carRepository.getOne(1));
		theModel.addAttribute("timetable",timetable);
		theModel.addAttribute("timetableToAdd",new Timetable());

		return "adminViews/adminTimetable/editDayForCar";
	}

	
	@RequestMapping(value = "/saveNew", method = RequestMethod.POST)
	public String saveNew(@RequestParam(value = "hour", required = false) String hour,
			@ModelAttribute("newTimetable") Timetable timetable, BindingResult result, Model theModel,
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date) 
	{	
		
		Timetable newTimetable = timetable;
		int begin = Integer.parseInt(hour);
		int end = begin+2;
		LocalDate day=date;
		newTimetable.setBegin(LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),begin,0,0));
		newTimetable.setEnd(LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),end,0,0));
		newTimetable.setInstructor(timetable.getInstructor());
		newTimetable.setCar(carRepository.getOne(1));
		timetableRepository.save(newTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", //timetableRepository.findAll());		
		timetableRepository.queryByDayAndMonthAndYearAndCar(date.getDayOfMonth(),date.getMonthValue(),date.getYear(),1));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("car",carRepository.getOne(1));
		theModel.addAttribute("today",LocalDate.now());
		theModel.addAttribute("timetableToAdd",new Timetable());
		Timetable timetable1=new Timetable();
		timetable1.setCar(carRepository.getOne(1));
		theModel.addAttribute("timetable",timetable);
		
		return "adminViews/adminTimetable/editDayForCar";
	}
	
	
	

}
