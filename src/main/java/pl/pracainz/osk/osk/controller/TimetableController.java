package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import javax.validation.Valid;

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
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(26, 8, 2019));
		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));

		// timetableRepository.queryByDayAndMonthAndYear(LocalDate.now().getDayOfMonth(),
		// LocalDate.now().getMonth().getValue(), LocalDate.now().getYear()));
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
	public String saveTimetable( @ModelAttribute("hourBegin") String hourBegin, BindingResult result,
			@ModelAttribute("timetable") Timetable theTimetable
		) 
			{
		LocalDateTime begin = LocalDateTime.of(theTimetable.getBegin().getYear(),theTimetable.getBegin().getMonth(),
			theTimetable.getBegin().getDayOfMonth(),10,1,1);
		
	//	LocalDateTime begin = LocalDateTime.of(2019, Month.AUGUST,26,15,30,30);

		LocalDateTime end = LocalDateTime.of(2019, Month.AUGUST,26,15,30,30);

	
	//	LocalDateTime end = LocalDateTime.of(theTimetable.getBegin().getYear(),theTimetable.getBegin().getMonth(),
		//theTimetable.getBegin().getDayOfWeek().getValue(),	hourBegin+2,0,0);
		theTimetable.setBegin(begin);
		theTimetable.setEnd(end);
		theTimetable.setCar(carRepository.getOne(1));
		
		timetableRepository.save(theTimetable);
		
		
		return "redirect:/timetable/list";
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
	
	
	@ModelAttribute("hours")
	public String[] getHours() {
	    return new String[] {
	        "6:00-8:00", "8:00-10:00", "10:00-12:00"
	        		, "12:00-14:00", 
	        "14:00-16:00", "16:00-18:00", "18:00-20:00"
	    };
	}
	
	

}
