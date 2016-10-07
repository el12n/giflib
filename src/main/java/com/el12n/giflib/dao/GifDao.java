package com.el12n.giflib.dao;

import com.el12n.giflib.model.Gif;

import java.util.List;

/**
 * Created by Alvaro De la Cruz on 10/6/2016.
 */
public interface GifDao {
    List<Gif> findAll();

    List<Gif> findByName(String name);

    Gif findById(long id);

    void save(Gif gif);

    void delete(Gif gif);
}
