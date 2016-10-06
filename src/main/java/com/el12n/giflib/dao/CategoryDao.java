package com.el12n.giflib.dao;

import com.el12n.giflib.model.Category;

import java.util.List;

/**
 * Created by alvarodelacruz on 6/10/16.
 */
public interface CategoryDao {

    List<Category> findAll();

    Category findById(long id);

    void save(Category category);

    void delete(Category category);

}
