package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.entity.Model;
import com.example.project.repository.CategoryRepository;
import com.example.project.repository.ModelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ModelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ModelRepository modelRepository;

    @Test
    void create() throws Exception {
        String name = "JUnit"; // имя для объекта
        Model model = new Model(name); // создал объект, чтобы отправить его
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8090/api/model") // отправил запрос
                .content(asJsonString(model))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    assertTrue(modelRepository.existsByName(name)); // проверил, есить ли в бд объект с нужным name
                });
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/model")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // запихнул результ в строку

                    JSONArray arr = new JSONArray(body); // переделал в JSON
                    JSONObject object = new JSONObject(arr.getString(0)); // взял 1й объект

                    assertEquals(object.get("id"), 1); // сверил id того объекта
                    assertEquals(object.get("name"), "iPhone-11-Pro-Max"); // сверил name того объекта
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/model/1")) // отправил запрос
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
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/model/name_iPhone-11-Pro-Max")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful()) // проверил, пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // запихнул результ в строку

                    JSONArray arr = new JSONArray(body); // переделал в JSON
                    JSONObject object = new JSONObject(arr.getString(0)); // взял 1й объект

                    assertEquals(object.get("name"), "iPhone-11-Pro-Max"); // сверил name c name из запроса (Smartphone)
                })
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8090/api/model/6")) // отправил запрос
                .andDo(MockMvcResultHandlers.print()) // вывел результат в консоль
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    assertFalse(modelRepository.existsById(6));
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