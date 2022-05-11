package laura.portfolio.gallery_art.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class SampleDataLoader {

    private PictureRepository pictureRepository;

    @Autowired
    public SampleDataLoader(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }

    @PostConstruct
    public void loadSampleData(){
        pictureRepository.saveAll(List.of(
                new Picture("pinky.jpeg", "The Pinky Fairy", 2021)
        ));
    }
}
