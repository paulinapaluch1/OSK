package pl.pracainz.osk.osk.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pracainz.osk.osk.dao.CategoryRepository;
import pl.pracainz.osk.osk.entity.Category;

@Controller
@RequestMapping("/courses")
public class CategoryController {

private CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
	   this.categoryRepository = categoryRepository;
	}
	

	/*
	@GetMapping("/list")
	public String listCategories(Model theModel) {
		
		List<Category> theCategories= categoryRepository.findAll();
		
		theModel.addAttribute("categories", theCategories);
		
		return "adminViews/adminCategories/categories";
	}
	*/
	
	
}
