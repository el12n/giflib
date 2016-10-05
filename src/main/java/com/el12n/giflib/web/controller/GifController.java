package com.el12n.giflib.web.controller;

import com.el12n.giflib.model.Gif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alvarodelacruz on 3/10/16.
 */
@Controller
public class GifController {
    ;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap) {
        List<Gif> allGifs = new ArrayList<>();
        modelMap.put("gifs", allGifs);
        return "gif/index";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(ModelMap modelMap, @PathVariable String name) {
        Gif gif = new Gif();
        modelMap.put("gif", gif);
        return "gif/details";
    }

}
