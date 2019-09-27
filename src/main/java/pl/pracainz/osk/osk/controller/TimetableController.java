package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
import pl.pracainz.osk.osk.dao.DrivingTypeRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.entity.DrivingType;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Timetable;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

	private TimetableRepository timetableRepository;
	private CarRepository carRepository;
	private InstructorRepository instructorRepository;
	private DrivingTypeRepository drivingTypeRepository;

	public TimetableController(TimetableRepository repository, CarRepository carRepository,
			InstructorRepository instructorRepository, DrivingTypeRepository drivingTypeRepository) {
		this.timetableRepository = repository;
		this.carRepository = carRepository;
		this.instructorRepository = instructorRepository;
		this.drivingTypeRepository = drivingTypeRepository;
	}

	@GetMapping("/list")
	public String showTimetable(Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());

		theModel.addAttribute("cars", carRepository.findAll());
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
				LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));

		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));
		return "adminViews/adminTimetable/timetable";
	}

	@RequestMapping("/earlierDate")
	public String changeDateForEarlier(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam(name = "page", required = false) String page, Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		LocalDate yesterday = date.minusDays(1);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		theModel.addAttribute("instructors", instructorRepository.findAll());

		if (page.contentEquals("cars")) {
			return "adminViews/adminTimetable/timetable";
		} else
			return "adminViews/adminTimetable/timetableByInstructors";

	}

	@RequestMapping("/nextDate")
	public String changeDateForLater(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam(name = "page", required = false) String page, Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		LocalDate yesterday = date.plusDays(1);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		theModel.addAttribute("instructors", instructorRepository.findAll());

		if (page.equals("cars")) {
			return "adminViews/adminTimetable/timetable";
		} else
			return "adminViews/adminTimetable/timetableByInstructors";

	}

	@RequestMapping(value = "/changeDate", method = RequestMethod.GET)
	public String changeDate(@ModelAttribute("date") @DateTimeFormat(iso = ISO.DATE_TIME) String date,
			 BindingResult result, Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		LocalDate yesterday = LocalDate.parse(date);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		theModel.addAttribute("instructors", instructorRepository.findAll());

		
			return "adminViews/adminTimetable/timetable";

	}

	@GetMapping("/edit")
	public String showFormForUpdate(@RequestParam("id_timetable") int id, Model theModel) {
		Optional<Timetable> theTimetable = timetableRepository.findById(id);
		Timetable thisTimetable = timetableRepository.getOne(id);

		LocalDate today = LocalDate.of(thisTimetable.getBegin().getYear(), thisTimetable.getBegin().getMonth(),
				thisTimetable.getBegin().getDayOfMonth());
		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", thisTimetable);
		theModel.addAttribute("today", today);
		return "adminViews/adminTimetable/timetableForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String changeInstructor(@ModelAttribute("timetable") Timetable theTimetable, Model theModel) {
		Timetable timetable = timetableRepository.getOne(theTimetable.getId());
		timetable.setInstructor(theTimetable.getInstructor());
		timetableRepository.save(timetable);
		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", timetable);
		return "adminViews/adminTimetable/timetableForm";
	}

	@RequestMapping(value = "/changeHours", method = RequestMethod.GET)
	public String changeHours(@RequestParam(value = "hour", required = false) String hour,
			@RequestParam(value = "hourBegin", required = false) String hourBegin,
			@ModelAttribute("timetable") Timetable theTimetable, BindingResult result, Model theModel) {
		Timetable timetable = timetableRepository.getOne(theTimetable.getId());
		int begin = Integer.parseInt(hour);
		int end = begin + 2;
		LocalDateTime beginOfPlannedDriving = LocalDateTime.of(timetable.getBegin().getYear(),
				timetable.getBegin().getMonth(), timetable.getBegin().getDayOfMonth(), begin, 0, 0);
		LocalDateTime endOfPlannedDriving = LocalDateTime.of(timetable.getEnd().getYear(),
				timetable.getEnd().getMonth(), timetable.getEnd().getDayOfMonth(), end, 0, 0);
		timetable.setBegin(beginOfPlannedDriving);
		timetable.setEnd(endOfPlannedDriving);

		timetableRepository.save(timetable);
		String editTitle = "EDYTUJ ZAPLANOWANĄ JAZDĘ";
		theModel.addAttribute("timetable", theTimetable);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("this", timetable);
		return "adminViews/adminTimetable/timetableForm";
	}

	@GetMapping("/editDayForCar")
	public String editDayForCar(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam("id") int id_car, Model theModel) {
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYearAndCar(
				date.getDayOfMonth(), date.getMonth().getValue(), date.getYear(), id_car));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("car", carRepository.getOne(id_car));
		theModel.addAttribute("today", date);
		Timetable timetable = new Timetable();
		timetable.setCar(carRepository.getOne(id_car));
		theModel.addAttribute("timetable", timetable);
		theModel.addAttribute("timetableToAdd", new Timetable());

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
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date
			) {
		Timetable theTimetable = timetableRepository.getOne(timetable.getId());
		int begin = Integer.parseInt(hour);
		int end = begin + 2;
		LocalDate day = date;
		theTimetable.setBegin(LocalDateTime.of(theTimetable.getBegin().getYear(), theTimetable.getBegin().getMonth(),
				theTimetable.getBegin().getDayOfMonth(), begin, 0, 0));
		theTimetable.setEnd(LocalDateTime.of(theTimetable.getBegin().getYear(), theTimetable.getBegin().getMonth(),
				theTimetable.getBegin().getDayOfMonth(), end, 0, 0));

			theTimetable.setCar(timetable.getCar());
		timetableRepository.save(theTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday",
				timetableRepository.queryByDayAndMonthAndYearAndCar(theTimetable.getBegin().getDayOfMonth(),
						theTimetable.getBegin().getMonthValue(), theTimetable.getBegin().getYear(),
						theTimetable.getCar().getId()));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("car", theTimetable.getCar());
		theModel.addAttribute("instructor",theTimetable.getInstructor());
		theModel.addAttribute("today", date);
		Timetable timetable1 = new Timetable();
		timetable1.setCar(theTimetable.getCar());
		theModel.addAttribute("timetable", timetable);
		theModel.addAttribute("timetableToAdd", new Timetable());

		return "adminViews/adminTimetable/editDayForCar";
	}

	@RequestMapping(value = "/saveNew", method = RequestMethod.POST)
	public String saveNew(@RequestParam(value = "hour", required = false) String hour,
			@ModelAttribute("newTimetable") Timetable timetable, BindingResult result, Model theModel,
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam(name = "id_car", required = false) Integer id_car) {
		Timetable newTimetable = timetable;
		int begin = Integer.parseInt(hour);
		int end = begin + 2;
		LocalDate day = date;
		newTimetable.setBegin(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), begin, 0, 0));
		newTimetable.setEnd(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), end, 0, 0));
		newTimetable.setInstructor(timetable.getInstructor());
		newTimetable.setCar(carRepository.getOne(id_car));
		newTimetable.setDrivingType(timetable.getDrivingType());

		timetableRepository.save(newTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYearAndCar(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), id_car));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("car", timetable.getCar());
		theModel.addAttribute("today", date);
		theModel.addAttribute("timetableToAdd", new Timetable());
		theModel.addAttribute("timetable", timetable);
		return "adminViews/adminTimetable/editDayForCar";
	}

	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
		return instructorRepository.findAll();
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id_timetable") int id, Model theModel) {
		Timetable timetable = timetableRepository.getOne(id);
		List<Timetable> timetablesToday = timetableRepository.queryByDayAndMonthAndYearAndCar(
				timetable.getBegin().getDayOfMonth(), timetable.getBegin().getMonth().getValue(),
				timetable.getBegin().getYear(), timetable.getCar().getId());
		timetablesToday.remove(timetableRepository.getOne(id));
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", timetablesToday);
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("car", timetable.getCar());
		theModel.addAttribute("today", LocalDate.of(timetable.getBegin().getYear(), timetable.getBegin().getMonth(),
				timetable.getBegin().getDayOfMonth()));
		Timetable editedTimetable = new Timetable();
		timetable.setCar(timetableRepository.getOne(id).getCar());
		theModel.addAttribute("timetable", editedTimetable);
		theModel.addAttribute("timetableToAdd", new Timetable());
		timetableRepository.deleteById(id);
		return "adminViews/adminTimetable/editDayForCar";
	}

	@ModelAttribute("drivingTypes")
	public List<DrivingType> drivingTypes() {
		return drivingTypeRepository.findAll();
	}

	@ModelAttribute("cars")
	public List<Car> cars() {
		return carRepository.findAll();

	}
	
	//grafik wg instruktorów
	@GetMapping("/byInstructors")
	public String showTimetableByInstructors(Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());

		// theModel.addAttribute("cars", carRepository.findAll());
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
				LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));
		return "adminViews/adminTimetable/timetableByInstructors";
	}

	@RequestMapping(value = "/changeDateWithInstructorsTimetable", method = RequestMethod.GET)
	public String changeDateWithInstructorsTimetable(@ModelAttribute("date") @DateTimeFormat(iso = ISO.DATE_TIME) String date,
			 BindingResult result, Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		LocalDate yesterday = LocalDate.parse(date);
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("dayName", getDayName(yesterday));
		theModel.addAttribute("instructors", instructorRepository.findAll());
		
		return "adminViews/adminTimetable/timetableByInstructors";

	}
	
	@GetMapping("/editDayForInstructor")
	public String editDayForInstructor(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam("id") int id_instructor, Model theModel) {
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYearAndInstructor(
				date.getDayOfMonth(), date.getMonth().getValue(), date.getYear(), id_instructor));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructor",instructorRepository.getOne(id_instructor));
		theModel.addAttribute("cars",carRepository.findAll());
		theModel.addAttribute("today", date);
		Timetable timetable = new Timetable();
		timetable.setInstructor(instructorRepository.getOne(id_instructor));
		theModel.addAttribute("timetable", timetable);
		theModel.addAttribute("timetableToAdd", new Timetable());

		return "adminViews/adminTimetable/editDayForInstructor";
	}
	
	
	@RequestMapping(value = "/saveChangedTimetableForInstructor", method = RequestMethod.GET)
	public String saveChangedTimetableForInstructor(@RequestParam(value = "hour", required = false) String hour,
			@ModelAttribute("timetable") Timetable timetable, BindingResult result, Model theModel,
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date
			) {
		Timetable theTimetable = timetableRepository.getOne(timetable.getId());
		int begin = Integer.parseInt(hour);
		int end = begin + 2;
		LocalDate day = date;
		theTimetable.setBegin(LocalDateTime.of(theTimetable.getBegin().getYear(), theTimetable.getBegin().getMonth(),
				theTimetable.getBegin().getDayOfMonth(), begin, 0, 0));
		theTimetable.setEnd(LocalDateTime.of(theTimetable.getBegin().getYear(), theTimetable.getBegin().getMonth(),
				theTimetable.getBegin().getDayOfMonth(), end, 0, 0));
		theTimetable.setInstructor(timetable.getInstructor());
		timetableRepository.save(theTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday",
				timetableRepository.queryByDayAndMonthAndYearAndInstructor(theTimetable.getBegin().getDayOfMonth(),
						theTimetable.getBegin().getMonthValue(), theTimetable.getBegin().getYear(),
						theTimetable.getInstructor().getId()));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructor",theTimetable.getInstructor());
		theModel.addAttribute("today", day);
		theModel.addAttribute("timetable", timetable);
		theModel.addAttribute("timetableToAdd", new Timetable());

		return "adminViews/adminTimetable/editDayForInstructor";
	}
	
	
	@RequestMapping(value = "/saveNewTimetableForInstructor", method = RequestMethod.POST)
	public String saveNewTimetableForInstructor(@RequestParam(value = "hour", required = false) String hour,
			@ModelAttribute("newTimetable") Timetable timetable, BindingResult result, Model theModel,
			@RequestParam(name = "today", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam(name = "id_instructor", required = false) Integer id_instructor) {
		Timetable newTimetable = timetable;
		int begin = Integer.parseInt(hour);
		int end = begin + 2;
		LocalDate day = date;
		newTimetable.setBegin(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), begin, 0, 0));
		newTimetable.setEnd(LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), end, 0, 0));
		newTimetable.setCar(timetable.getCar());
		newTimetable.setInstructor(instructorRepository.getOne(id_instructor));
		newTimetable.setDrivingType(timetable.getDrivingType());
		timetableRepository.save(newTimetable);
		String editTitle = "EDYTUJ ZAPLANOWANE JAZDY";
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYearAndInstructor(date.getDayOfMonth(), date.getMonthValue(), date.getYear(), id_instructor));
		theModel.addAttribute("title", editTitle);
		theModel.addAttribute("instructor", timetable.getInstructor());
		theModel.addAttribute("today", date);
		theModel.addAttribute("timetableToAdd", new Timetable());
		theModel.addAttribute("timetable", timetable);
		return "adminViews/adminTimetable/editDayForInstructor";
	}
	
}
