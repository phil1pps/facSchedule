package com.example.facSchedule;

import com.example.facSchedule.model.SpecialityModel;
import com.example.facSchedule.service.SpecialityService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log
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
    public void shouldPostSpec() throws Exception {
        MvcResult result = mockMvc.perform(post("/deanery/addSpeciality").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"specialityName\":\"testSpeciality\"}"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void shouldGetSpec() throws Exception {
        mockMvc.perform(get("/deanery/getSpecialities"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(authorities = "STUDENT")
    public void shouldNotRedirectAfterStudentSubjectGroup() throws Exception {
        mockMvc.perform(get("/deanery/getSpecialities"))
                .andExpect(status().is4xxClientError());
    }



}
