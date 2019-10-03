package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.InternalExamRepository;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.dao.TimetableRepository;
import pl.pracainz.osk.osk.dao.UserRepository;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Driving;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.InstructorOpinion;
import pl.pracainz.osk.osk.entity.InternalExam;
import pl.pracainz.osk.osk.entity.Student;
import pl.pracainz.osk.osk.entity.User;
import pl.pracainz.osk.osk.entity.UserPrincipal;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
	private InstructorRepository instructorRepository;
	private InternalExamRepository internalExamRepository;
	private UserRepository userRepository;
	private TimetableRepository timetableRepository;
	private StudentRepository studentRepository;

	public InstructorController(InstructorRepository repository, InternalExamRepository exams,
			UserRepository userRepository,TimetableRepository timetableRepository, StudentRepository studentRepository) {
		this.instructorRepository = repository;
		this.internalExamRepository = exams;
		this.userRepository = userRepository;
		this.timetableRepository = timetableRepository;
		this.studentRepository = studentRepository;
	}

	@GetMapping("/list")
	public String listInstructors(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findByDeleted(0);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructors";
	}

	@GetMapping("/listArchived")
	public String listArhivedInstructors(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findByDeleted(1);
		theModel.addAttribute("instructors", theInstructors);
		return "adminViews/adminInstructors/instructorsArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("instructor", new Instructor());
		return "adminViews//adminInstructors/instructorForm";
	}

	@GetMapping("/showFormForUpdate")

	public String showFormForUpdate(@RequestParam("id_instructor") int id,
									Model theModel) {
		theModel.addAttribute("instructor", instructorRepository.findById(id));
		return "adminViews/adminInstructors/instructorForm";			

	}

	@PostMapping("save")
	public String saveInstructor(@ModelAttribute("instructor") Instructor theInstructor) {
		theInstructor.setDeleted(0);
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/list";
	}

	@GetMapping("/archiveInstructor")
	public String archiveInstructor(@RequestParam("id_instructor") int id, Model theModel) {
		Instructor theInstructor = instructorRepository.getOne(id);
		theInstructor.setDeleted(1);
		instructorRepository.save(theInstructor);

		return "redirect:/instructors/list";
	}

	@GetMapping("/unarchiveInstructor")
	public String unarchiveInstructor(@RequestParam("id_instructor") int id, Model theModel) {
		Instructor theInstructor = instructorRepository.getOne(id);
		theInstructor.setDeleted(0);
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/listArchived";
	}

	@GetMapping("/profile")
	public String showProfile(Model theModel) {
		List<Instructor> theInstructors = instructorRepository.findAll();
		theModel.addAttribute("instructors", theInstructors);
		theModel.addAttribute("instructor", getCurrentLoggedInstructor());
		return "instructorViews/instructorProfile";
	}

	@GetMapping("/updateDataInstructor")
	public String instructorDataUpdate(@RequestParam("id_instructor") int id, Model theModel) {
		Optional<Instructor> theInstructor = instructorRepository.findById(id);
		theModel.addAttribute("instructor", theInstructor);
		return "instructorViews/editInstructorProfile";
	}

	@PostMapping("/data/save")
	public String saveDataInstructor(@ModelAttribute("instructor") Instructor theInstructor) {
		instructorRepository.save(theInstructor);
		return "redirect:/instructors/profile";
	}

	@GetMapping("/showCourses")
	public String listCourses(Model theModel) {
		List<Course> theCourses = instructorRepository.queryFindCourses(getCurrentLoggedInstructorId());
		theModel.addAttribute("courses", theCourses);
		return "instructorViews/instructorCourses/courses";
	}
	
	@GetMapping("/showExams")
	public String listExams(Model theModel) {
		List<InternalExam> theExams = instructorRepository.queryFindExams(getCurrentLoggedInstructorId());
		theModel.addAttribute("internalexams", theExams);
		return "instructorViews/instructorExams/exams";
	}
	
	
	@GetMapping("/showExamForm")
	public String addExams(Model theModel) {
		InternalExam theInternalExam = new InternalExam();
		theModel.addAttribute("internalexam", theInternalExam);
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("students", studentRepository.findAll());
		// theModel.addAttribute("internalexam", theInternalExam);
		return "instructorViews/instructorExams/examForm";
	}
	
	@GetMapping("/showExamFormForUpdate")
	public String showExamUpdate(@RequestParam("id_internalExam") int id, Model theModel) {
		Optional<InternalExam> theInternalExam = internalExamRepository.findById(id);
		theModel.addAttribute("internalexam", theInternalExam);
		return "instructorViews/instructorExams/examForm";
	}
	
	
	@GetMapping("/exams/save")
	public String saveDataExam(@ModelAttribute("internalexam") InternalExam theInternalExam) {
		internalExamRepository.save(theInternalExam);
		return "redirect:/instructors/showExams";
	}
	
	@GetMapping("/showArchivedExams")
	public String listArchivedExams(Model theModel) {
		List<InternalExam> theExams = instructorRepository.findArchivedExams(getCurrentLoggedInstructorId());
		theModel.addAttribute("internalexams", theExams);
		return "instructorViews/instructorExams/examsArchived";
	}
	
	@GetMapping("/showStudents")
	public String listStudents(Model theModel) {
		List<Student> theStudents = instructorRepository.queryFindStudents(getCurrentLoggedInstructorId());
		theModel.addAttribute("students", theStudents);
		return "instructorViews/instructorStudents/students";
	}
	
	@GetMapping("/showOpinions")
	public String listOpinions(Model theModel) {
		List<InstructorOpinion> theOpinions = instructorRepository.queryFindOpinions(getCurrentLoggedInstructorId());
		theModel.addAttribute("instructoropinions", theOpinions);
		return "instructorViews/instructorOpinions/opinionsAboutInstructor";
	}
	
	@GetMapping("/showDrivings")
	public String listDrivings(Model theModel) {
		List<Driving> theDrivings = instructorRepository.findUndoneDrivings(getCurrentLoggedInstructorId());
		theModel.addAttribute("drivings", theDrivings);
		return "instructorViews/instructorDrivings/drivings";
	}
	
	@GetMapping("/showDoneDrivings")
	public String listDoneDrivings(Model theModel) {
		List<Driving> theDrivings = instructorRepository.findDoneDrivings(getCurrentLoggedInstructorId());
		theModel.addAttribute("drivings", theDrivings);
		return "instructorViews/instructorDrivings/drivingsDone";
	}
	
	@GetMapping("/showTimetable")
	public String listTimetable(Model theModel) {
	//	List<Timetable> theTimetable = instructorRepository.queryFindTimetable(1);
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYearAndInstructor(
				LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear(),
				instructorRepository.getOne(getCurrentLoggedInstructorId()).getId()));
		theModel.addAttribute("today", LocalDate.now());
		theModel.addAttribute("dayName", getDayName(LocalDate.now()));	
		theModel.addAttribute("instructor",getCurrentLoggedInstructor());
		return "instructorViews/instructorTimetable/timetable";
	}

	@RequestMapping("/changeDateForEarlierForInstructor")
	public String changeDateForEarlier(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date
			, Model theModel) {
		
		LocalDate yesterday = date.minusDays(1);
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYearAndInstructor(
				yesterday.getDayOfMonth(), yesterday.getMonthValue(), yesterday.getYear(),
				instructorRepository.getOne(getCurrentLoggedInstructorId()).getId()));
		theModel.addAttribute("today", yesterday);
		theModel.addAttribute("dayName", getDayName(yesterday));	
		theModel.addAttribute("instructor",getCurrentLoggedInstructor());
		return "instructorViews/instructorTimetable/timetable";
	}
	
	
	@RequestMapping("/changeDateForLaterForInstructor")
	public String changeDateForLater(
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate date
			, Model theModel) {
		LocalDate tomorrow = date.plusDays(1);
		theModel.addAttribute("timetablesToday", timetableRepository.queryByDayAndMonthAndYearAndInstructor(
				tomorrow.getDayOfMonth(), tomorrow.getMonthValue(), tomorrow.getYear(),
				instructorRepository.getOne(getCurrentLoggedInstructorId()).getId()));
		theModel.addAttribute("today", tomorrow);
		theModel.addAttribute("dayName", getDayName(tomorrow));	
		theModel.addAttribute("instructor",getCurrentLoggedInstructor());	
		return "instructorViews/instructorTimetable/timetable";
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
	
	private Instructor getCurrentLoggedInstructor() {
		User user = userRepository.findByUsername(getCurrentUserName());
		return instructorRepository.findByUserId(user.getId());
	}
	
	private int getCurrentLoggedInstructorId() {
		return getCurrentLoggedInstructor().getId();
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

	
	
}
