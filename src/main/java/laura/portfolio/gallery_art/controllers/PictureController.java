package laura.portfolio.gallery_art.controllers;

import laura.portfolio.gallery_art.repositories.Picture;
import laura.portfolio.gallery_art.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PictureController {

    private PictureRepository pictureRepository;

    @Autowired
    public PictureController(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }

    @GetMapping("/pictures")
    String listPictures(Model model){
        List<Picture> pictures=(List<Picture>) pictureRepository.findAll();
        model.addAttribute("title","Gallery");
        model.addAttribute("pictures", pictures);
        return "pictures/all";
    }

    @GetMapping("pictures/new")
    String getForm(Model model){
        Picture picture = new Picture();
        model.addAttribute("title", "Add a new picture");
        model.addAttribute("picture", picture);
        return"pictures/new";
    }

    @PostMapping("/pictures/new")
    String addPicture(@ModelAttribute Picture picture) {
        pictureRepository.save(picture);
        return "redirect:/pictures";
    }

}
