package com.el12n.giflib.service;

import com.el12n.giflib.dao.GifDao;
import com.el12n.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alvaro De la Cruz on 10/6/2016.
 */
@Service
public class GifServiceImpl implements GifService {

    @Autowired
    private GifDao gifDao;

    @Override
    public List<Gif> findAll() {
        return gifDao.findAll();
    }

    @Override
    public List<Gif> findByDescription(String description) {
        return gifDao.findByDescription(description);
    }

    @Override
    public Gif findById(Long id) {
        return gifDao.findById(id);
    }

    @Override
    public void save(Gif gif, MultipartFile file) {
        try {
            gif.setBytes(file.getBytes());
            gifDao.save(gif);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Gif gif) {
        gifDao.delete(gif);
    }

    @Override
    public List<Gif> findFavorites() {
        return gifDao.findAll().stream().filter(Gif::isFavorite).collect(Collectors.toList());
    }

    @Override
    public void toggleFavorite(Gif gif) {
        gif.setFavorite(!gif.isFavorite());
        gifDao.save(gif);
    }
}
