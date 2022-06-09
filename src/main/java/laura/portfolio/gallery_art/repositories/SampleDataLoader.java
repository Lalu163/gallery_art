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
                new Picture("pinky.jpeg", "The Pinky Fairy", 2021),
                new Picture("arcangel.jpeg", "The Justice", 2021),
                new Picture("autumm.jpeg", "Autumm", 2022),
                new Picture("leaf-fairy.jpeg", "The Fairy Of The Leaf", 2022),
                new Picture("alive.jpeg", "Alive", 2021)
        ));
    }
}
