package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.entity.Company;
import com.example.project.repository.CompanyRepository;
import com.example.project.service.CompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void create() throws Exception {
        String name = "JUnit"; // имя для объекта
        Company company = new Company(name); // создал объект, чтобы отправить его
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8090/api/company") // отправил запрос
                .content(asJsonString(company))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    assertTrue(companyRepository.existsByName(name)); // проверил, есить ли в бд объект с нужным name
                });
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // запихнул результ в строку
                    JSONArray arr = new JSONArray(body); // переделал в JSON
                    JSONObject object = new JSONObject(arr.getString(0)); // взял 1й объект
                    assertEquals(object.get("id"), 1); // сверил id того объекта
                    assertEquals(object.get("name"), "Apple"); // сверил name того объекта
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company/1")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // запихнул результ в строку
                    JSONObject object = new JSONObject(body); // переделал в JSON
                    assertEquals(object.get("id"), 1); // сверил id с id из запроса (1)
                })
                .andReturn();
    }

    @Test
    void findByName() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/company/name_Apple")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // запихнул результ в строку

                    JSONArray arr = new JSONArray(body); // переделал в JSON
                    JSONObject object = new JSONObject(arr.getString(0)); // взял 1й объект

                    assertEquals(object.get("name"), "Apple"); // сверил name c name из запроса (Smartphone)
                })
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8090/api/company/6"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    assertFalse(companyRepository.existsById(6));
                });
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}