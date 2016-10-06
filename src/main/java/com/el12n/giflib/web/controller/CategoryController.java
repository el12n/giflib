package com.el12n.giflib.web.controller;

import com.el12n.giflib.model.Category;
import com.el12n.giflib.model.Gif;

import com.el12n.giflib.service.CategoryService;
import com.el12n.giflib.web.Color;
import com.el12n.giflib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarodelacruz on 4/10/16.
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/categories")
    public String getCategories(ModelMap modelMap) {
        List<Category> categories = categoryService.findAll();

        modelMap.put("categories", categories);
        return "category/index";
    }

    @RequestMapping("/category/{id}")
    public String category(@PathVariable int id, ModelMap modelMap) {
        Category category = new Category();
        modelMap.put("category", category);

        List<Gif> gifs = new ArrayList<>();
        modelMap.put("gifs", gifs);

        return "category/details";
    }

    @RequestMapping("categories/add")
    public String formNewCategory(Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", new Category());
        }
        model.addAttribute("colors", Color.values());

        model.addAttribute("heading", "New Category");
        model.addAttribute("action", "/categories");
        model.addAttribute("submit", "Add");
        return "category/form";
    }

    @RequestMapping("categories/{categoryId}/edit")
    public String formEditCategory(@PathVariable Long categoryId, Model model) {
        if (!model.containsAttribute("category")) {
            model.addAttribute("category", categoryService.findById(categoryId));
        }
        model.addAttribute("colors", Color.values());

        model.addAttribute("heading", "Edit Category");
        model.addAttribute("action", String.format("/categories/%s", categoryId));
        model.addAttribute("submit", "Update");
        return "category/form";
    }

    @RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.POST)
    public String updateCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
            redirectAttributes.addFlashAttribute("category", category);
            return String.format("redirect:/categories/%s/edit", category.getId());
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully updated!", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String addCategory(@Valid Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category", result);
            redirectAttributes.addFlashAttribute("category", category);
            return "redirect:/categories/add";
        }
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully added!", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }

    @RequestMapping(value = "/categories/{categoryId}/delete", method = RequestMethod.POST)
    public String deleteCategory(@PathVariable Long categoryId, RedirectAttributes redirectAttributes) {
        Category category = categoryService.findById(categoryId);
        if (category.getGifs().size() > 0) {
            redirectAttributes.addFlashAttribute("flash", new FlashMessage("Only empty categories can be deleted!", FlashMessage.Status.FAILURE));
            return String.format("redirect:/categories/%s/edit", categoryId);
        }
        categoryService.delete(category);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully deleted!", FlashMessage.Status.SUCCESS));
        return "redirect:/categories";
    }
}
