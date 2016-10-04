package com.el12n.giflib.data;

import com.el12n.giflib.model.Gif;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alvarodelacruz on 4/10/16.
 */
@Component
public class GifRepository {

    private static final List<Gif> ALL_GIFS = Arrays.asList(
            new Gif("android-explosion", 1, LocalDate.of(2015, 2, 13), "Alvaro Lockhart", false),
            new Gif("ben-and-mike", 2, LocalDate.of(2015, 10, 30), "Emmanuel Ponciano", true),
            new Gif("book-dominos", 3, LocalDate.of(2015, 9, 15), "Brayler Sanchez", false),
            new Gif("compiler-bot", 1, LocalDate.of(2015, 2, 13), "Ada Lovelace", true),
            new Gif("cowboy-coder", 1, LocalDate.of(2015, 2, 13), "Wilson Hamilton", false),
            new Gif("infinite-andrew", 3, LocalDate.of(2015, 8, 23), "Emmanuel Reyes", true)
    );

    public Gif findByName(String name) {
        for (Gif gif : ALL_GIFS) {
            if (gif.getName().equals(name)) return gif;
        }
        return null;
    }

    public List<Gif> getAllGifs() {
        return ALL_GIFS;
    }

    public List<Gif> findByCategoryId(int id) {
        List<Gif> gifs = new ArrayList<Gif>();
        for (Gif gif : ALL_GIFS) {
            if (gif.getCategoryId() == id) gifs.add(gif);
        }
        return gifs;
    }
}
