package laura.portfolio.gallery_art;

import laura.portfolio.gallery_art.repositories.Picture;
import laura.portfolio.gallery_art.repositories.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void loadsTheHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Autowired
    PictureRepository pictureRepository;

    @Test
    void returnsTheExistingPictures() throws Exception {

        Picture picture = pictureRepository.save(new Picture("pinky.jpeg", "The Pinky Fairy", "2021"));
        mockMvc.perform(get("/pictures"))
                .andExpect(status().isOk())
                .andExpect(view().name("pictures/all"))
                .andExpect(model().attribute("pictures", hasItem(picture)));
    }

    @Test
    void returnsAFormToAddNewPicture() throws Exception {
        mockMvc.perform(get("/pictures/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pictures/edit"))
                .andExpect(model().attributeExists("picture"))
                .andExpect(model().attribute("title", "Add a new picture"));
    }

    @Test
    void allowsToCreateANewPicture() throws Exception {
        mockMvc.perform(post("/pictures/new")
                        .param("img", "pinky.jpeg")
                        .param("title", "The Pinky Fairy")
                        .param("year", "2021")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pictures"));

        List<Picture> existingPictures = (List<Picture>) pictureRepository.findAll();
        assertThat(existingPictures, contains(allOf(
                hasProperty("img", equalTo("pinky.jpeg")),
                hasProperty("title", equalTo("The Pinky Fairy")),
                hasProperty("year", equalTo("2021"))
        )));
    }

    @Test
    void returnsAFormToEditPictures() throws Exception {
        Picture picture = pictureRepository.save(new Picture("pinky.jpeg", "The Pinky Fairy", "2021"));
        mockMvc.perform(get("/pictures/edit/" + picture.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("pictures/edit"))
                .andExpect(model().attribute("picture", picture))
                .andExpect(model().attribute("title", "Edit picture"));
    }

    @Test
    void loadsTheWhoIAmPage() throws Exception {
        mockMvc.perform(get("/who-i-am"))
                .andExpect(status().isOk())
                .andExpect(view().name("who-i-am"));
    }

    @Test
    void allowsToDeleteAPicture()throws Exception{
        Picture picture = pictureRepository.save(new Picture("pinky.jpeg", "The Pinky Fairy", "2021"));
        mockMvc.perform(get("/pictures/delete/" + picture.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pictures"));

        assertThat(pictureRepository.findById(picture.getId()), equalTo(Optional.empty()));
    }
}
