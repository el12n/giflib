package com.el12n.giflib.controller;

import com.el12n.giflib.model.Gif;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by alvarodelacruz on 3/10/16.
 */
@Controller
public class GifController {

    @RequestMapping("/")
    public String listGifs() {
        return "home";
    }

    @RequestMapping("/gif")
    public String gifDetails(ModelMap modelMap) {
        Gif gif = new Gif("cowboy-coder", new Date(), "el12n", false);
        modelMap.put("gif", gif);
        return "gif-details";
    }

}
