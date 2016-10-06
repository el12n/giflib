package com.el12n.giflib.web.controller;

import com.el12n.giflib.model.Gif;
import com.el12n.giflib.service.CategoryService;
import com.el12n.giflib.service.GifService;
import com.el12n.giflib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Created by alvarodelacruz on 3/10/16.
 */
@Controller
public class GifController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GifService gifService;

    @RequestMapping("/")
    public String listGifs(Model model) {
        model.addAttribute("gifs", gifService.findAll());
        return "gif/index";
    }

    @RequestMapping("/gifs/{gifId}")
    public String gifDetails(@PathVariable Long gifId, Model model) {
        model.addAttribute("gif", gifService.findById(gifId));
        return "gif/details";
    }

    @RequestMapping("/gifs/{gifId}.gif")
    @ResponseBody
    public byte[] gifImage(@PathVariable Long gifId) {
        return gifService.findById(gifId).getBytes();
    }

    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        if (!model.containsAttribute("gif")) {
            model.addAttribute("gif", new Gif());
        }
        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("heading", "Upload");
        model.addAttribute("action", "/gifs");
        model.addAttribute("submit", "Add");
        return "gif/form";
    }

    @RequestMapping("/gifs/{gifId}/edit")
    public String formEditGif(@PathVariable Long gifId, Model model) {
        if (!model.containsAttribute("gif")) {
            model.addAttribute("gif", gifService.findById(gifId));
        }
        model.addAttribute("categories", categoryService.findAll());

        model.addAttribute("heading", "Edit Gif");
        model.addAttribute("action", String.format("/gifs/%s", gifId));
        model.addAttribute("submit", "Update");
        return "gif/form";
    }

    @RequestMapping(value = {"/gifs", "/gifs/{gifId}"}, method = RequestMethod.POST)
    public String addGif(Gif gif, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        gifService.save(gif, file);
        redirectAttributes.addFlashAttribute(
                "flash",
                new FlashMessage("Gif successfully uploaded!", FlashMessage.Status.SUCCESS));
        return String.format("redirect:/gifs/%s", gif.getId());
    }

}
