package pl.pracainz.osk.osk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.pracainz.osk.osk.dao.CategoryRepository;
import pl.pracainz.osk.osk.dao.CourseRepository;
import pl.pracainz.osk.osk.dao.InstructorRepository;
import pl.pracainz.osk.osk.dao.ParticipantRepository;
import pl.pracainz.osk.osk.dao.ParticipantService;
import pl.pracainz.osk.osk.dao.StudentRepository;
import pl.pracainz.osk.osk.entity.Category;
import pl.pracainz.osk.osk.entity.Course;
import pl.pracainz.osk.osk.entity.Instructor;
import pl.pracainz.osk.osk.entity.Participant;
import pl.pracainz.osk.osk.entity.Student;

@Controller
@RequestMapping("/courses")
public class CourseController {

	private CourseRepository courseRepository;
	private InstructorRepository instructorRepository;
	private CategoryRepository categoryRepository;
	private StudentRepository studentRepository;
	private ParticipantRepository participantRepository;
	@Autowired
	private ParticipantService participantService;

	public CourseController(CourseRepository repository, InstructorRepository instructorRepository,
			CategoryRepository categoryRepository, StudentRepository studentRepository,
			ParticipantRepository participantRepository) {
		this.courseRepository = repository;
		this.instructorRepository = instructorRepository;
		this.categoryRepository = categoryRepository;
		this.studentRepository = studentRepository;
		this.participantRepository = participantRepository;
	}

	@GetMapping("/list")
	public String listCourses(Model theModel) {
		theModel.addAttribute("courses", courseRepository.findByFinished(0));
		return "adminViews/adminCourses/courses";
	}

	@GetMapping("/listArchived")
	public String listArchivedCourses(Model theModel) {
		theModel.addAttribute("courses", courseRepository.findByFinished(1));
		return "adminViews/adminCourses/coursesArchived";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		theModel.addAttribute("course", new Course());
		return "adminViews/adminCourses/courseForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_course") int id, Model theModel) {
		theModel.addAttribute("course", courseRepository.findById(id));
		return "adminViews/adminCourses/courseForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String validateForm(@Valid Course course, BindingResult result, Model theModel) {
		if (result.hasErrors()) {
			return "adminViews/adminCourses/courseForm";
		}
		return saveCourse(course, theModel);
	}

	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String saveCourse(@ModelAttribute("course") Course theCourse, Model theModel) {
		theCourse.setFinished(0);
		courseRepository.save(theCourse);
		theModel.addAttribute("saved", true);
		return listCourses(theModel);
	}

	@GetMapping("/archiveCourse")
	public String archiveCourse(@RequestParam("id_course") int id, Model theModel) {
		Course theCourse = courseRepository.getOne(id);
		theCourse.setFinished(1);
		courseRepository.save(theCourse);
		return "redirect:/courses/list";
	}

	@GetMapping("/unarchiveCourse")
	public String unarchiveCourse(@RequestParam("id_course") int id, Model theModel) {
		Course theCourse = courseRepository.getOne(id);
		theCourse.setFinished(0);
		courseRepository.save(theCourse);
		return "redirect:/courses/listArchived";
	}

	@GetMapping("/categories")
	public String listCategories(Model theModel) {

		return "adminViews/adminCourses/categories";
	}

	@PostMapping("saveEditedCategory")
	public String saveEditedCategory(@ModelAttribute("category") Category theCategory, Model theModel) {
		Category category = categoryRepository.getOne(theCategory.getId());
		category.setCategoryName(theCategory.getCategoryName());
		categoryRepository.save(category);
		theModel.addAttribute("changed", true);
		return listCategories(theModel);
	}

	@PostMapping("saveNewCategory")
	public String saveNewCategory(@ModelAttribute("categoryToAdd") Category theCategory, Model theModel) {
		categoryRepository.save(theCategory);
		theModel.addAttribute("added", true);
		return listCategories(theModel);
	}

	@ModelAttribute("categories")
	public List<Category> categories() {
		return categoryRepository.findAll();
	}

	@ModelAttribute("instructors")
	public List<Instructor> instructors() {
		return instructorRepository.findByDeleted(0);
	}

	@ModelAttribute("category")
	public Category category() {
		return new Category();
	}

	@ModelAttribute("categoryToAdd")
	public Category categoryToAdd() {
		return new Category();
	}

	@GetMapping("/showStudents")
	public String listParticipants(@RequestParam("id_course") int id, Model theModel) {
		List<Student> students = courseRepository.findParticipants(id);
		theModel.addAttribute("course", courseRepository.getOne(id));
		theModel.addAttribute("students", students);
		return "adminViews/adminCourses/participants";
	}

	@RequestMapping("/deleteParticipant")

	public String deleteParticipants(@RequestParam("id_student") int id_student,
			@RequestParam("id_course") int id_course, Model theModel) {
		participantService.deleteParticipant(id_student, id_course);

		return listParticipants(id_course, theModel);
	}

	@GetMapping("/addParticipants")
	public String addParticipants(Model theModel, @RequestParam("id") int courseId) {
		Course course = courseRepository.getOne(courseId);
		Participant participant = new Participant();
		participant.getPrimaryKey().setCourse(course);
		theModel.addAttribute("participant", participant);
		theModel.addAttribute("students", studentRepository.findAll());
		theModel.addAttribute("courses", courseRepository.findAll());
		return "adminViews/adminCourses/addParticipant";
	}

	@PostMapping("/participants/save")
	public String saveNewParticipant(@ModelAttribute("participant") Participant theParticipant, Model theModel) {
		participantRepository.save(theParticipant);
		return listParticipants(theParticipant.getPrimaryKey().getCourse().getId(), theModel);
	}

}
