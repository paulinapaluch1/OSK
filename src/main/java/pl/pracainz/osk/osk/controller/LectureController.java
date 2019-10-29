package pl.pracainz.osk.osk.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.CategoryRepository;
import pl.pracainz.osk.osk.dao.CourseRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.LectureRepository;
import pl.pracainz.osk.osk.entity.Category;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Lecture;

@Controller
@RequestMapping("/lectures")
public class LectureController {
	private LectureRepository lectureRepository;
	private CourseRepository courseRepository;
	private CategoryRepository categoryRepository;
	private InstructorRepository instructorRepository;

	public LectureController(LectureRepository repository, CourseRepository course, CategoryRepository category,
			InstructorRepository instructor) {
		this.lectureRepository = repository;
		this.courseRepository = course;
		this.categoryRepository = category;
		this.instructorRepository = instructor;
	}

	@GetMapping("/list")
	public String listLectures(Model theModel) {
		theModel.addAttribute("lectures", lectureRepository.findByDeleted(0));
		return "adminViews/adminLectures/lectures";
	}

	@GetMapping("/listArchived")
	public String listArchivedLectures(Model theModel) {
		theModel.addAttribute("lectures", lectureRepository.findByDeleted(1));
		return "adminViews/adminLectures/lecturesArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("date", LocalDate.now());
		theModel.addAttribute("time", LocalTime.now());
		theModel.addAttribute("lecture", new Lecture());
		return "adminViews/adminLectures/lectureForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_lecture") int id, Model theModel) {
		theModel.addAttribute("date", LocalDate.now());
		theModel.addAttribute("time", LocalTime.now());
		theModel.addAttribute("lecture", lectureRepository.getOne(id));
		return "adminViews/adminLectures/lectureForm";
		
	}

	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	@PostMapping("/save")
	public String saveLecture(@ModelAttribute("lecture") Lecture theLecture, 
			@RequestParam("date") @DateTimeFormat(iso = ISO.DATE_TIME) String date,
			BindingResult result,Model theModel
	) {
		LocalDate lectureDate = LocalDate.parse(date);

		theLecture.setDate(LocalDateTime.of(lectureDate, LocalTime.of(10, 10)));
		lectureRepository.save(theLecture);
		return "redirect:/lectures/list";
	}
/*
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String validateForm(@Valid Lecture lecture, BindingResult result, Model theModel,
			@RequestParam(name = "date", required=false) @DateTimeFormat(iso = ISO.DATE) LocalDate date, 
			@RequestParam(name = "time") @DateTimeFormat(iso = ISO.TIME) LocalTime time
			) {
		//if (result.hasErrors()) {
		//	return "adminViews/adminLectures/lectureForm";
		//}
		return saveLecture(lecture, date, time,result, theModel);
	}
*/
	@GetMapping("/archiveLecture")
	public String archiveLecture(@RequestParam("id_lecture") int id, Model theModel) {
		Lecture theLecture = lectureRepository.getOne(id);
		theLecture.setDeleted(1);
		lectureRepository.save(theLecture);
		return "redirect:/lectures/list";
	}

	@GetMapping("/unarchiveLecture")
	public String unarchiveLecture(@RequestParam("id_lecture") int id, Model theModel) {
		Lecture theLecture = lectureRepository.getOne(id);
		theLecture.setDeleted(0);
		lectureRepository.save(theLecture);
		return "redirect:/lectures/listArchived";
	}

	@ModelAttribute("categories")
	public List<Category> categories() {
		return categoryRepository.findAll();
	}

	@ModelAttribute("courses")
	public List<Course> courses() {
		return courseRepository.findByFinished(0);
	}

	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
		return instructorRepository.findByDeleted(0);
	}

}
