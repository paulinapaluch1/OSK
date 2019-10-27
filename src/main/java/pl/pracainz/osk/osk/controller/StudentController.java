package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.PasswordGenerator;
import pl.pracainz.osk.osk.dao.CarOpinionRepository;
import pl.pracainz.osk.osk.dao.CarRepository;
import pl.pracainz.osk.osk.dao.CourseRepository;
import pl.pracainz.osk.osk.dao.DrivingRepository;
import pl.pracainz.osk.osk.dao.InstructorOpinionRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.ParticipantRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.CarOpinion;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Participant;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.Timetable;
import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.entity.UserPrincipal;

@Controller
@RequestMapping("/students")
public class StudentController {

	private StudentRepository studentRepository;
	private InstructorRepository instructorRepository;
	private InstructorOpinionRepository instructorOpinionRepository;
	private CarOpinionRepository carOpinionRepository;
	private CourseRepository courseRepository;
	private DrivingRepository drivingRepository;
	private TimetableRepository timetableRepository;
	private CarRepository carRepository;
	private UserRepository userRepository;
	private PasswordEncoder encoder;

<<<<<<< HEAD
	@Autowired 
	private ParticipantRepository participantRepository;

=======
>>>>>>> da31c313b64c63a264840e3c220b99479f8eaa6e
	public StudentController(StudentRepository repository, InstructorRepository instructor,
			InstructorOpinionRepository instructorOpinion, DrivingRepository drivingRepository,
			TimetableRepository timetableRepository, CarRepository carRepository, CarOpinionRepository carOpinion,
			UserRepository userRepository, CourseRepository courseRepository, PasswordEncoder encoder) {
		this.studentRepository = repository;
		this.instructorRepository = instructor;
		this.instructorOpinionRepository = instructorOpinion;
		this.drivingRepository = drivingRepository;
		this.timetableRepository = timetableRepository;
		this.carRepository = carRepository;
		this.carOpinionRepository = carOpinion;
		this.userRepository = userRepository;
		this.courseRepository = courseRepository;
		this.encoder = encoder;
	}

	@GetMapping("/list")
	public String listStudents(Model theModel) {
		theModel.addAttribute("students", studentRepository.findByDeleted(0));
		return "adminViews/adminStudents/students";
	}

	@GetMapping("/listArchived")
	public String listArchivedStudents(Model theModel) {
		theModel.addAttribute("students", studentRepository.findByDeleted(1));
		return "adminViews/adminStudents/studentsArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("student", new Student());
		theModel.addAttribute("pkk", "");
		theModel.addAttribute("action", "add");
		return "adminViews/adminStudents/addStudent";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_student") int id, Model theModel) {
		theModel.addAttribute("student", studentRepository.findById(id));
		theModel.addAttribute("pkk", studentRepository.getOne(id).getPkk());
		theModel.addAttribute("action", "update");
		return "adminViews/adminStudents/addStudent";

	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String saveStudent(@ModelAttribute("student") Student theStudent, @RequestParam("action") String action) {
		if (action.contentEquals("add")) {
			String password = PasswordGenerator.generatePassword(20);
			User user = new User(theStudent.getLogin(), encoder.encode(password), "STUDENT", "");
			userRepository.save(user);
			UserPrincipal principal = new UserPrincipal(user);
			theStudent.setUserId(principal.getId());
		} else {
			User user = userRepository.findById(theStudent.getUserId());
			user.setUsername(theStudent.getLogin());
			userRepository.save(user);
		}
		theStudent.setDeleted(0);
		studentRepository.save(theStudent);
		return "redirect:/students/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String validateForm(@Valid Student student, BindingResult result, Model theModel,
			@RequestParam("action") String action) {
		if (result.hasErrors()) {
			return "adminViews/adminStudents/addStudent";
		}
		return saveStudent(student, action);
	}

	@GetMapping("/archiveStudent")
	public String archiveStudent(@RequestParam("id_student") int id, Model theModel) {
		Student theStudent = studentRepository.getOne(id);
		theStudent.setDeleted(1);
		studentRepository.save(theStudent);
		return "redirect:/students/list";
	}

	@GetMapping("/unarchiveStudent")
	public String unarchiveStudent(@RequestParam("id_student") int id, Model theModel) {

		Student theStudent = studentRepository.getOne(id);
		theStudent.setDeleted(0);
		studentRepository.save(theStudent);
		return "redirect:/students/listArchived";
	}

	@GetMapping("/profile")
	public String showProfile(Model theModel) {
		List<Student> theStudents = studentRepository.findAll();
		theModel.addAttribute("students", theStudents);
		theModel.addAttribute("student", getCurrentLoggedStudent());

		return "studentViews/studentProfile";
	}

	@GetMapping("/updateDataStudent")
	public String studentDataUpdate(@RequestParam("id_student") int id, Model theModel) {
		Optional<Student> theStudent = studentRepository.findById(id);
		theModel.addAttribute("student", theStudent);
		return "studentViews/editStudentProfile";
	}

	@PostMapping("/data/save")
	public String saveDataStudent(@ModelAttribute("student") Student theStudent) {
		studentRepository.save(theStudent);
		return "redirect:/students/profile";
	}

	@GetMapping("/showInstructors")
	public String listInstructors(Model theModel) {
		List<Instructor> theInstructors = studentRepository.findInstructorsForOneStudent(getCurrentLoggedStudentId());
		Set<Integer> ratedInstructorsIds = new HashSet<>();
		Integer currentLoggedStudentId = getCurrentLoggedStudent().getId();
		for (Instructor instructor : theInstructors) {
			for (InstructorOpinion opinion : instructor.getInstructorOpinions()) {
				if (opinion.getStudent().getId() == currentLoggedStudentId) {
					ratedInstructorsIds.add(instructor.getId());
				}
			}
		}
		theModel.addAttribute("ratedInstructorsIds", ratedInstructorsIds);
		theModel.addAttribute("instructors", theInstructors);
		return "studentViews/studentInstructors/instructors";
	}

	@GetMapping("/showCars")
	public String listCars(Model theModel) {
		List<Car> theCars = studentRepository.findCarsForOneStudentById(getCurrentLoggedStudentId());
		Set<Integer> ratedCarsIds = new HashSet<>();
		Integer currentLoggedStudentId = getCurrentLoggedStudent().getId();
		for (Car car : theCars) {
			for (CarOpinion opinion : car.getCarOpinions()) {
				if (opinion.getStudent().getId() == currentLoggedStudentId) {
					ratedCarsIds.add(car.getId());
				}
			}
		}
		theModel.addAttribute("ratedCarsIds", ratedCarsIds);
		theModel.addAttribute("cars", theCars);
		return "studentViews/studentCars/cars";
	}

	@GetMapping("/showDrivings")
	public String listDrivings(Model theModel) {
		List<Driving> theDrivings = studentRepository.findDrivingsForStudent(getCurrentLoggedStudentId());
		theModel.addAttribute("drivings", theDrivings);
		return "studentViews/studentDrivings/drivings";
	}

	@GetMapping("/showDoneDrivings")
	public String listDoneDrivings(Model theModel) {
		List<Driving> theDrivings = studentRepository.findDoneDrivingsForStudentById(getCurrentLoggedStudentId());
		theModel.addAttribute("drivings", theDrivings);
		return "studentViews/studentDrivings/drivingsDone";
	}

	@GetMapping("/showCancelledDrivings")
	public String listCancelledDrivings(Model theModel) {
		List<Driving> theDrivings = studentRepository.findCancelledDrivings(getCurrentLoggedStudentId());
		theModel.addAttribute("drivings", theDrivings);
		return "studentViews/studentDrivings/drivingsCancelled";
	}

	@GetMapping("/showCourses")
	public String listCourses(Model theModel) {
		List<Course> theCourses = studentRepository.findCoursesForStudent(getCurrentLoggedStudentId());
		theModel.addAttribute("courses", theCourses);
		return "studentViews/studentCourses/courses";
	}

	@GetMapping("/showExams")
	public String listExams(Model theModel) {
		List<InternalExam> theExams = studentRepository.findStudentExams(getCurrentLoggedStudentId());
		theModel.addAttribute("internalexams", theExams);
		return "studentViews/studentExams/exams";
	}

	@GetMapping("/rateInstructors")
	public String rateInstructors(@RequestParam("id_instructor") int id, Model theModel) {
		InstructorOpinion theInstructorOpinion = new InstructorOpinion();
		theInstructorOpinion.setInstructor(instructorRepository.getOne(id));
		theModel.addAttribute("rated", instructorOpinionRepository.checkIfRated(getCurrentLoggedStudentId()));
		theModel.addAttribute("instructoropinion", theInstructorOpinion);
		theModel.addAttribute("instructor", instructorRepository.getOne(id));
		return "studentViews/studentInstructors/rateInstructors";
	}

	@PostMapping("/saveInstructorOpinion")
	public String saveInstructorOpinion(@RequestParam("id_instructor") int id,
			@ModelAttribute("instructoropinion") InstructorOpinion theInstructorOpinion) {
		theInstructorOpinion.setInstructor(instructorRepository.getOne(id));
		theInstructorOpinion.setStatus("nowa");
		theInstructorOpinion.setDeleted(0);
		theInstructorOpinion.setStudent(getCurrentLoggedStudent());
		instructorOpinionRepository.save(theInstructorOpinion);
		return "redirect:/students/showInstructors";
	}

	@GetMapping("/rateCars")
	public String rateCars(@RequestParam("id_car") int id, Model theModel) {
		CarOpinion theCarOpinion = new CarOpinion();
		theCarOpinion.setCar(carRepository.getOne(id));
		theModel.addAttribute("caropinion", theCarOpinion);
		theModel.addAttribute("car", carRepository.getOne(id));
		return "studentViews/studentCars/rateCars";
	}

	@GetMapping("/showCarOpinion")
	public String showCarOpinion(@RequestParam("id_car") int id, Model theModel) {
		CarOpinion theCarOpinion = getCarOpinionGivenByLoggedStudentForThisCar(id);
		if (theCarOpinion != null) {
			theModel.addAttribute("caropinion", theCarOpinion);
			theModel.addAttribute("car", carRepository.getOne(id));

			return "studentViews/studentCars/showCarOpinion";
		} else
			return listCars(theModel);
	}

	private CarOpinion getCarOpinionGivenByLoggedStudentForThisCar(Integer id) {
		for (CarOpinion opinion : carRepository.getOne(id).getCarOpinions()) {
			if (opinion.getStudent().getId() == getCurrentLoggedStudent().getId())
				return opinion;
		}
		return null;
	}

	@GetMapping("/showInstructorOpinion")
	public String showInstructorOpinion(@RequestParam("id_instructor") int id, Model theModel) {
		InstructorOpinion theInstructorOpinion = getCarOpinionGivenByLoggedStudentForThisInstructor(id);
		if (theInstructorOpinion != null) {
			theModel.addAttribute("instructoropinion", theInstructorOpinion);
			theModel.addAttribute("instructor", instructorRepository.getOne(id));

			return "studentViews/studentInstructors/showInstructorOpinion";
		} else
			return listCars(theModel);
	}

	private InstructorOpinion getCarOpinionGivenByLoggedStudentForThisInstructor(Integer id) {
		for (InstructorOpinion opinion : instructorRepository.getOne(id).getInstructorOpinions()) {
			if (opinion.getStudent().getId() == getCurrentLoggedStudent().getId())
				return opinion;
		}
		return null;
	}

	@PostMapping("/saveCarOpinion")
	public String saveCarOpinion(@RequestParam("id_car") int id,
			@ModelAttribute("caropinion") CarOpinion theCarOpinion) {
		theCarOpinion.setCar(carRepository.getOne(id));
		theCarOpinion.setStatus("nowa");
		theCarOpinion.setDeleted(0);
		theCarOpinion.setStudent(getCurrentLoggedStudent());
		carOpinionRepository.save(theCarOpinion);
		return "redirect:/students/showCars";
	}

	private String getCurrentUserName() {
		String username = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserPrincipal) {
			username = ((UserPrincipal) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	private Student getCurrentLoggedStudent() {
		User user = userRepository.findByUsername(getCurrentUserName());
		return studentRepository.findByUserId(user.getId());
	}

	private int getCurrentLoggedStudentId() {
		return getCurrentLoggedStudent().getId();
	}

	// dla admina
	@GetMapping("/listCourses")
	public String listCoursesForStudent(@RequestParam("id_student") int id, Model theModel) {
		List<Course> theCourses = courseRepository.findCourses(id);
		theModel.addAttribute("courses", theCourses);
		return "adminViews/adminStudents/courses";
	}

	@GetMapping("/checkPKK")
	public String checkPKK(@ModelAttribute("pkk") String pkk, Model theModel) {
		if (studentRepository.findByPkk(pkk) == null) {
			theModel.addAttribute("student", new Student());
			theModel.addAttribute("pkk", "");
			theModel.addAttribute("action", "add");

		} else {
			theModel.addAttribute("student", studentRepository.findByPkk(pkk));
			theModel.addAttribute("pkk", studentRepository.findByPkk(pkk).getPkk());
			theModel.addAttribute("action", "update");

		}
		return "adminViews/adminStudents/addStudent";
	}

	@GetMapping("/showTimetable")
	public String showTimetableForStudentToReserved(Model theModel) {
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(
				LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));

		return "studentViews/studentTimetable/timetable";
	}

	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
		return instructorRepository.findAll();
	}

	@RequestMapping("/changeDateForEarlierInStudentsTimetable")
	public String changeDateForEarlier(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			Model theModel) {
		LocalDate yesterday = date.minusDays(1);
		theModel.addAttribute("timetablesToday", timetableRepository
				.queryByDayAndMonthAndYear(yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear()));
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("dayName", getDayName(yesterday));
		return "studentViews/studentTimetable/timetable";
	}

	@RequestMapping("/changeDateForLaterInStudentsTimetable")
	public String changeDateForLater(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			Model theModel) {
		LocalDate tomorrow = date.plusDays(1);
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(tomorrow.getDayOfMonth(),
				tomorrow.getMonthValue(), tomorrow.getYear()));
		theModel.addAttribute("today", tomorrow);
		theModel.addAttribute("dayName", getDayName(tomorrow));
		return "studentViews/studentTimetable/timetable";
	}

	@GetMapping("/reservePlannedDriving")
	public String reservePlannedDriving(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date,
			@RequestParam("id_timetable") int id, Model theModel) {
			boolean canReserve = false;
		
		for(Participant participant : getCurrentLoggedStudent().getParticipants()){
		if(participant.getNumberHoursUsed()>=28 && participant.getNumberHoursPaid()>=2) {
			participant.setNumberHoursUsed(participant.getNumberHoursUsed()+2);
			canReserve = true;
			participantRepository.save(participant);
			break;
	
		}
		}
		if(canReserve == true) {
			Timetable timetableToReserve = timetableRepository.getOne(id);
			Driving driving = new Driving();
			driving.setTimetable(timetableToReserve);
			driving.setStudent(getCurrentLoggedStudent());
			driving.setDone(0);
			driving.setCancelled(0);
			driving.setDeleted(0);
			drivingRepository.save(driving);
			timetableToReserve.getDrivings().add(driving);
			timetableRepository.save(timetableToReserve);
		}
		
		
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYear(date.getDayOfMonth(),
				date.getMonthValue(), date.getYear()));
		theModel.addAttribute("today", date);
		theModel.addAttribute("dayName", getDayName(date));
		return "studentViews/studentTimetable/timetable";
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

	@GetMapping("/changePassword")
	public String changePassword(Model theModel) {
		String newPassword = "";
		String oldPassword = "";
		String repeatedPassword = "";
		theModel.addAttribute("newPassword", newPassword);
		theModel.addAttribute("oldPassword", oldPassword);
		theModel.addAttribute("repeatedPassword", repeatedPassword);
		theModel.addAttribute("error", "");

		return "studentViews/changePassword";

	}

	@RequestMapping("/checkPassword")
	public String checkPassword(@RequestParam("repeatedPassword") String password,
			@ModelAttribute("newPassword") String newPassword, @RequestParam("oldPassword") String oldPassword,
			Model theModel) {
		User user = userRepository.findByUsername(getCurrentUserName());
		oldPassword="student5";
		if (!oldPassword.equals(encoder.encode(user.getPassword()))) {
			theModel.addAttribute("error", "Stare hasło jest niepoprawne");
			return "studentViews/changePassword";
		} else if (!newPassword.equals(password)) {
			theModel.addAttribute("error", "Hasła są różne");
			return "studentViews/changePassword";
		} else {
			theModel.addAttribute("error", "");
		return changeUserPassword(newPassword);
		}
	}

	public String changeUserPassword(String password) {
		User user = userRepository.findByUsername(getCurrentUserName());
		user.setPassword(encoder.encode(password));
		userRepository.save(user);

		return "studentViews/studentProfile";
	}

}
