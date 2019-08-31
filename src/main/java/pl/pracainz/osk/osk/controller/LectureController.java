package pl.pracainz.osk.osk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.CategoryRepository;
import pl.pracainz.osk.osk.dao.CourseRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.LectureRepository;
import pl.pracainz.osk.osk.entity.Car;
import pl.pracainz.osk.osk.entity.Lecture;

@Controller
@RequestMapping("/lectures")
public class LectureController {
	private LectureRepository lectureRepository;
	private CourseRepository courseRepository;
	private CategoryRepository categoryRepository;
	private InstructorRepository instructorRepository;
	

	public LectureController(LectureRepository repository, CourseRepository course, CategoryRepository category, InstructorRepository instructor) {
		this.lectureRepository = repository;
		this.courseRepository = course;
		this.categoryRepository = category;
		this.instructorRepository = instructor;
	}

	@GetMapping("/list")
	public String listLectures(Model theModel) {

		List<Lecture> theLectures = lectureRepository.findByDeleted(0);

		theModel.addAttribute("lectures", theLectures);

		return "adminViews/adminLectures/lectures";
	}
	
	@GetMapping("/listArchived")
	public String listArchivedLectures(Model theModel) {

		List<Lecture> theLectures = lectureRepository.findByDeleted(1);

		theModel.addAttribute("lectures", theLectures);

		return "adminViews/adminLectures/lecturesArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Lecture theLecture = new Lecture();

		theModel.addAttribute("lecture", theLecture);
		theModel.addAttribute("courses", courseRepository.findAll());
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("categories", categoryRepository.findAll());
		return "adminViews/adminLectures/lectureForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_lecture") int id, Model theModel) {

		Optional<Lecture> theLecture = lectureRepository.findById(id);
		theModel.addAttribute("lecture", theLecture);
		theModel.addAttribute("courses", courseRepository.findAll());
		theModel.addAttribute("instructors", instructorRepository.findAll());
		theModel.addAttribute("categories", categoryRepository.findAll());
		
		return "adminViews/adminLectures/lectureForm";
	}

	@PostMapping("save")
	public String saveLecture(@ModelAttribute("lecture") Lecture theLecture) {
		lectureRepository.save(theLecture);

		return "redirect:/lectures/list";
	}
	
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

}
