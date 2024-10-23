package Prototype_Backend.prototype;

import Prototype_Backend.prototype.Controllers.LaboController;
import Prototype_Backend.prototype.entities.Laboratoire;
import Prototype_Backend.prototype.repositories.LaboRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LaboController.class)
public class LaboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaboRepository laboRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateLaboratoire() throws Exception {

        Laboratoire laboratoire = new Laboratoire();
        laboratoire.setName("Laboratoire A");
        laboratoire.setNrc("RC987654");
        laboratoire.setLogo("www.example.com");
        laboratoire.setActive(true);

        when(laboRepository.save(any(Laboratoire.class))).thenReturn(laboratoire);

        mockMvc.perform(post("/api/laboratories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(laboratoire)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laboratoire A"))
                .andExpect(jsonPath("$.nrc").value("RC987654"));

        // Vérifier si la méthode save a été appelée une fois
        Mockito.verify(laboRepository, Mockito.times(1)).save(any(Laboratoire.class));
    }
}
