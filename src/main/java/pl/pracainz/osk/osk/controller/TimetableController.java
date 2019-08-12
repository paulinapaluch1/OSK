package pl.pracainz.osk.osk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.TimetableRepository;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

private TimetableRepository timetableRepository;
	
	public TimetableController(TimetableRepository repository) {
	this.timetableRepository=repository;
	}

	@GetMapping("/list")
	public String showTimetable() {
		
		return "adminViews/adminTimetable/timetable";
	}
	
	
	
}
