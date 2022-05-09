package laura.portfolio.gallery_art.controllers;

import laura.portfolio.gallery_art.repositories.Picture;
import laura.portfolio.gallery_art.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("title","Picture list");
        model.addAttribute("pictures", pictures);
        return "pictures/all";
    }
}
