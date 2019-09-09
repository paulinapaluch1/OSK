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
import pl.pracainz.osk.osk.entity.Category;
import pl.pracainz.osk.osk.entity.Course;


@Controller
@RequestMapping("/courses")
public class CourseController {

private CourseRepository courseRepository;
private InstructorRepository instructorRepository;
private CategoryRepository categoryRepository;

	public CourseController(CourseRepository repository,InstructorRepository instructorRepository,CategoryRepository categoryRepository) {
	   this.courseRepository = repository;
	   this.instructorRepository=instructorRepository;
	   this.categoryRepository = categoryRepository;
	}
	

	
	@GetMapping("/list")
	public String listCourses(Model theModel) {
		List<Course> theCourses= courseRepository.findByFinished(0);
		theModel.addAttribute("courses", theCourses);
		return "adminViews/adminCourses/courses";
	}
	
	@GetMapping("/listArchived")
	public String listArchivedCourses(Model theModel) {
		List<Course> theCourses= courseRepository.findByFinished(1);
		theModel.addAttribute("courses", theCourses);
		return "adminViews/adminCourses/coursesArchived";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Course theCourse = new Course();
		theModel.addAttribute("course", theCourse);
		theModel.addAttribute("instructors",instructorRepository.findAll() );
		theModel.addAttribute("categories",categoryRepository.findAll() );
		return "adminViews/adminCourses/courseForm";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id_course") int id,
									Model theModel) {
		Optional<Course> theCourse = courseRepository.findById(id);
		theModel.addAttribute("course", theCourse);
		theModel.addAttribute("instructors",instructorRepository.findAll() );
		theModel.addAttribute("categories",categoryRepository.findAll() );
		return "adminViews/adminCourses/courseForm";		
	}
	

	@PostMapping("save")
	public String saveCourse(@ModelAttribute("course") Course theCourse) {
		courseRepository.save(theCourse);
		return "redirect:/courses/list";
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
		List<Category> theCategories= categoryRepository.findAll();
		theModel.addAttribute("category", new Category());
		theModel.addAttribute("categories", theCategories);
		theModel.addAttribute("categoryToAdd", new Category());		
		return "adminViews/adminCourses/categories";
	}
	
	@PostMapping("saveEditedCategory")
	public String saveEditedCategory(@ModelAttribute("category") Category theCategory, Model theModel) {
		Category category = categoryRepository.getOne(theCategory.getId());
		category.setCategoryName(theCategory.getCategoryName());
		categoryRepository.save(category);
		theModel.addAttribute("category", new Category());
		theModel.addAttribute("categories", categoryRepository.findAll());
		theModel.addAttribute("categoryToAdd", new Category());			
		return "redirect:/courses/categories";
	}
	
	@PostMapping("saveNewCategory")
	public String saveNewCategory(@ModelAttribute("categoryToAdd") Category theCategory, Model theModel) {
		categoryRepository.save(theCategory);
		theModel.addAttribute("category", new Category());
		theModel.addAttribute("categories", categoryRepository.findAll());
		theModel.addAttribute("categoryToAdd", new Category());		
		return "redirect:/courses/categories";
	}
	
	
	
	
	
}
