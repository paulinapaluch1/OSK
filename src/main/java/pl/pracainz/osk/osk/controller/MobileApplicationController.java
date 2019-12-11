package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.pracainz.osk.osk.dao.GpsPointRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.GpsPoint;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;
import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.json.InstructorJson;
import pl.pracainz.osk.osk.json.LocationJson;
import pl.pracainz.osk.osk.json.Message;
import pl.pracainz.osk.osk.json.TimetableJson;
import pl.pracainz.osk.osk.service.MobileService;

@RestController
@RequestMapping("/mobile")
public class MobileApplicationController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	InstructorRepository instructorRepository;
	
	//@Autowired
	MobileService service;

	@Autowired
	GpsPointRepository gpsRepository;
	
	@Autowired
	TimetableRepository timetableRepository;
	@Autowired 
	StudentRepository studentRepository;
	
	public MobileApplicationController(MobileService service) {
		this.service = service;
	}

	@GetMapping("login/{username}/{password}")
	public InstructorJson loginToMobileApp(@PathVariable String username, @PathVariable("password") String password) {
		InstructorJson json;
		User user = userRepository.findByUsername(username);
		if (null != user && encoder.matches(password, user.getPassword())
				&& instructorRepository.findByUserId(user.getId()) != null) {
			Instructor instructor = instructorRepository.findByUserId(user.getId());
			json = new InstructorJson("true", instructor.getId(), instructor.getName(), instructor.getSurname(),
					instructor.getEmail(),instructor.getLogin(),instructor.getPhoneNumber(), instructor.getMarkAverage());;
		} else {
			json = new InstructorJson("false");
		}
		return json;
	}
	
	@PostMapping("send/{instructor}")
	public Message getCoordinates(@RequestBody ArrayList<LocationJson> coordinates,
			@PathVariable("instructor") Integer id) {
		
		GpsPoint gps;
		for(LocationJson json : coordinates) {
			gps=new GpsPoint(json.getNs(), json.getNw(), json.getTime(), 1,1);
			gpsRepository.save(gps);
			
		}
		
		
		return new Message("true");
	}
	
	
    @GetMapping("timetable/{id}")
    public List<TimetableJson> getTimetable(@PathVariable("id") Integer id){
    	List<TimetableJson> timetableJsonList = new ArrayList<>();
    	LocalDateTime today = LocalDateTime.now();
    
    	List<Timetable> timetables = timetableRepository.queryByDayAndMonthAndYearAndInstructor( today.getDayOfMonth(),
    			today.getMonthValue(), today.getYear(), id);
    	TimetableJson json;
    	List<Student> students = studentRepository.findAll();
    	for(int i=1;i<timetables.size();i++) {	
    	json = new TimetableJson( students.get(i).getName()+" "+students.get(i).getSurname(),
    			timetables.get(i).getBegin().getHour()+".00-"+timetables.get(i).getEnd().getHour()+".00",timetables.get(i).getDrivingType().getType(),
    			getDayName(today)+" "+today.getDayOfMonth()+"."+today.getMonthValue()+"."+today.getYear()+"r.");
    	timetableJsonList.add(json);
    	}
    	
    	return timetableJsonList;
    	
    }

	@GetMapping("hello")
	public String sayHello() {
		return "Hello world!";
	}

	
	
	
	
	public String getDayName(LocalDateTime date) {
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
}
