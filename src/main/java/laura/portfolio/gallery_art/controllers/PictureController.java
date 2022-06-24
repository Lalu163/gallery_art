package laura.portfolio.gallery_art.controllers;

import laura.portfolio.gallery_art.repositories.Picture;
import laura.portfolio.gallery_art.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    String newPicture(Model model){
        Picture picture = new Picture();
        model.addAttribute("title", "Add a new picture");
        model.addAttribute("picture", picture);
        return "pictures/edit";
    }

    @GetMapping("pictures/edit/{id}")
    String editPicture(Model model, @PathVariable Long id){
        Picture picture = pictureRepository.findById(id).get();
        model.addAttribute("picture", picture);
        model.addAttribute("title", "Edit picture");
        return "pictures/edit";
    }

    @PostMapping("/pictures/new")
    String addPicture(@ModelAttribute Picture picture) {
        pictureRepository.save(picture);
        return "redirect:/pictures";
    }

}
