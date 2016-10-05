package com.el12n.giflib.web.controller;

import com.el12n.giflib.model.Category;
import com.el12n.giflib.model.Gif;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarodelacruz on 4/10/16.
 */
@Controller
public class CategoryController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/categories")
    public String getCategories(ModelMap modelMap) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        criteria.from(Category.class);
        List<Category> categories = session.createQuery(criteria).getResultList();

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
