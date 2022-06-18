package laura.portfolio.gallery_art;

import laura.portfolio.gallery_art.repositories.Picture;
import laura.portfolio.gallery_art.repositories.PictureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

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

        Picture picture = pictureRepository.save(new Picture("img", "The Pink Fairy", 2001));
        mockMvc.perform(get("/pictures"))
                .andExpect(status().isOk())
                .andExpect(view().name("pictures/all"))
                .andExpect(model().attribute("pictures", hasItem(picture)));
    }

    @Test
    void returnsAFormToAddNewPicture() throws Exception {
        mockMvc.perform(get("/pictures/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pictures/new"));
    }

    @Test
    void allowsToCreateANewPicture() throws Exception {
        mockMvc.perform(post("/pictures/new")
                .param("title", "The Futuristic Fairy")
                .param("year", "2021")
    )
        .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pictures"));

        List<Picture> existingPictures = (List<Picture>) pictureRepository.findAll();
        assertThat(existingPictures, contains(allOf(
                hasProperty("title", equalTo("The Futuristic Fairy")),
                hasProperty("year"), equalTo(2021)
        )));
    }

    @Test
    void loadsTheWhoIAmPage() throws Exception {
        mockMvc.perform(get("/who-i-am"))
                .andExpect(status().isOk())
                .andExpect(view().name("who-i-am"));
    }
}
