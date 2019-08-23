package pl.pracainz.osk.osk.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	this.timetableRepository=repository;
	this.carRepository=carRepository;
	this.instructorRepository=instructorRepository;
	}

	@GetMapping("/list")
	public String showTimetable( Model theModel) {
		List<Timetable> theTimetable= timetableRepository.findAll();
		theModel.addAttribute("timetables",theTimetable);
		theModel.addAttribute("cars",carRepository.findAll());
	
		theModel.addAttribute("timetablesToday",timetableRepository.queryByDayAndMonthAndYear(LocalDateTime.now().getDayOfMonth(),LocalDateTime.now().getMonth().getValue(),LocalDateTime.now().getYear()));//Calendar.getInstance().get(Calendar.MONTH),2019));//date.get(Calendar.MONTH),date.get(Calendar.YEAR)));
		return "adminViews/adminTimetable/timetable";
	}

	
}
