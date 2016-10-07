package com.el12n.giflib.dao;

import com.el12n.giflib.model.Gif;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Alvaro De la Cruz on 10/6/2016.
 */
@Repository
public class GifDaoImpl implements GifDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Gif> findAll() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Gif> criteria = builder.createQuery(Gif.class);
        criteria.from(Gif.class);
        List<Gif> gifs = session.createQuery(criteria).getResultList();

        session.close();
        return gifs;
    }

    @Override
    public List<Gif> findByName(String name) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Gif> criteria = builder.createQuery(Gif.class);
        Root<Gif> gifRoot = criteria.from(Gif.class);
        criteria.select(gifRoot);
        criteria.where(builder.like(gifRoot.get("description"), "%" + name + "%"));
        List<Gif> foundGifs = session.createQuery(criteria).getResultList();

        session.close();

        return foundGifs;
    }

    @Override
    public Gif findById(long id) {
        Session session = sessionFactory.openSession();
        Gif gif = session.get(Gif.class, id);
        session.close();
        return gif;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void save(Gif gif) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(gif);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void delete(Gif gif) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(gif);
        session.getTransaction().commit();
        session.close();
    }
}
