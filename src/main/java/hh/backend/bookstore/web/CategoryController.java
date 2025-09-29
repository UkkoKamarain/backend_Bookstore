package hh.backend.bookstore.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {

    CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository cr) {
        this.categoryRepository = cr;
    }

    // http://localhost:8080/categorylist
    @GetMapping("/categorylist")
    public String getCategoryList(
        Model model
    ) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categorylist"; // categorylist.html
    }

    // http://localhost:8080/addcategory
    @GetMapping("/addcategory")
    public String getAddCategory(
        Model model
    ) {
        model.addAttribute("category", new Category());
        return "addcategory"; // addcategory.html
    }

    @RequestMapping("/savecategory")
    public String reqSaveBook(
        @ModelAttribute Category category
    ) {
        categoryRepository.save(category);
        return "redirect:/categorylist"; // back to categorylist
    }
}
