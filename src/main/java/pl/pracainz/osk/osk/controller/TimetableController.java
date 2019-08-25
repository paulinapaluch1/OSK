package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;

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
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(22, 8, 2019));
		theModel.addAttribute("today", LocalDate.now());
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
	    theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
					yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
	    theModel.addAttribute("dayName",getDayName(yesterday));
		
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
	    theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
					yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
	    theModel.addAttribute("dayName",getDayName(yesterday));
	
	    return "adminViews/adminTimetable/timetable";
	}
	
	
	
	
	@RequestMapping(value="/changeDate", method = RequestMethod.GET)
	public String changeDate( 
			@ModelAttribute("date") @DateTimeFormat(iso = ISO.DATE_TIME) String date,BindingResult result,
			Model theModel) {
		theModel.addAttribute("timetables", timetableRepository.findAll());
		theModel.addAttribute("cars", carRepository.findAll());
		LocalDate yesterday = LocalDate.parse(date);
		theModel.addAttribute("today", yesterday);
	    theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
					yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
	    theModel.addAttribute("dayName",getDayName(yesterday));
	
	    return "adminViews/adminTimetable/timetable";
	}
	
	
	
	
	
	
	public String getDayName(LocalDate date) {
		int dayNumber = date.getDayOfWeek().getValue();		
		switch(dayNumber) { 
		case 1: return "Poniedziałek ";
		case 2: return "Wtorek "; 
		case 3: return "Środa "; 
		case 4: return "Czwartek ";
		case 5: return "Piątek ";
		case 6: return "Sobota ";
		case 7: return "Niedziela ";
		default: return "Dzisiaj ";
		
		}
		
	}


}
