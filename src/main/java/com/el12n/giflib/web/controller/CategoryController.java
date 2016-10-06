package com.el12n.giflib.web.controller;

import com.el12n.giflib.model.Category;
import com.el12n.giflib.model.Gif;

import com.el12n.giflib.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
