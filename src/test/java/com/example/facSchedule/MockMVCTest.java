package com.example.facSchedule;

import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.service.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMVCTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SpecialityService specialityService;


    @BeforeEach
    public void mockService() throws Exception {doReturn(new ArrayList<SpecialityModel>()).when(specialityService).getAllSpeciality();}

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void shouldReturnViewWithSubjectGroups() throws Exception {
        mockMvc.perform(get("/deanery/getSpecialities"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotRedirectAfterStudentSubjectGroup() throws Exception {
/*        SubjectGroupListDTO form = new SubjectGroupListDTO();
        mockMvc.perform(post("/student/subject/groups").flashAttr("form", form).with(csrf()))
                .andExpect(status().is4xxClientError());*/
        mockMvc.perform(get("/deanery/getSpecialities"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void should() throws Exception {
      /*doThrow(new NotFoundException("No specialities!")).when(specialityService).getAllSpeciality();
        mockMvc.perform(get("/deanery/getSpecialities"))
                .andExpect(status().isOk());*/
    }


   /* @GetMapping("/getSpecialities")
    public ResponseEntity getSpecialities() {
        try {
            return ResponseEntity.ok(specialityService.getAllSpeciality());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

}
