package com.example.project.controller;

import com.example.project.entity.Company;
import com.example.project.service.CompanyService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CompanyService companyService;
    
    @Test
    void create() throws Exception {
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company")) // отправляем запрос
                .andDo(MockMvcResultHandlers.print()) // выводим в консоль
                .andExpect(status().is2xxSuccessful()) // проверяем пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // записываем то что пришло в строку
                    JSONArray arr = new JSONArray(body); // переделываем в JSON
                    JSONObject object = new JSONObject(arr.getString(0));
                    assertEquals(object.get("id"), 1);
                    assertEquals(object.get("name"), "Apple");
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString();
                    JSONObject object = new JSONObject(body);
                    assertEquals(object.get("id"), 1);
                })
                .andReturn();
    }

    @Test
    void findByName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company/name_Apple"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString();

                    JSONArray arr = new JSONArray(body);
                    JSONObject object = new JSONObject(arr.getString(0));

                    assertEquals(object.get("name"), "Apple");
                })
                .andReturn();
    }

    @Test
    void update() throws Exception {
    }

    @Test
    void delete() throws Exception {
    }
}