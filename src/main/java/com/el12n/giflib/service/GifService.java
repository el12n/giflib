package com.el12n.giflib.service;

import com.el12n.giflib.model.Gif;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Alvaro De la Cruz on 10/6/2016.
 */
public interface GifService {
    List<Gif> findAll();

    Gif findById(Long id);

    void save(Gif gif, MultipartFile file);

    void delete(Gif gif);
}
