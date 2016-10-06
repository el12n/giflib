package com.el12n.giflib.dao;

import com.el12n.giflib.model.Category;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by alvarodelacruz on 6/10/16.
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);
        criteria.from(Category.class);
        List<Category> categories = session.createQuery(criteria).getResultList();

        session.close();

        return categories;
    }

    @Override
    public Category findById(long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, (int) id);
        Hibernate.initialize(category.getGifs());
        session.close();
        return category;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(category);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void delete(Category category) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(category);
        session.getTransaction().commit();
        session.close();
    }
}
