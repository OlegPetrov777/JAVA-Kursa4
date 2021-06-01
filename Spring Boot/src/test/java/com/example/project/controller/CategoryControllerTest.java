package com.example.project.controller;

import com.example.project.entity.Category;
import com.example.project.repository.CategoryRepository;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void create() throws Exception {
        String name = "JUnit";
        Category category = new Category(name);
        this.mvc.perform(MockMvcRequestBuilders.post("http://localhost:8090/api/category")
                .content(asJsonString(category))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    assertTrue(categoryRepository.existsByName(name));
                });
    }

    @Test
    void findAll() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/category")) // отправляем запрос
                .andDo(MockMvcResultHandlers.print()) // выводим в консоль
                .andExpect(status().is2xxSuccessful()) // проверяем пришел ли статус 200
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString(); // записываем то что пришло в строку

                    JSONArray arr = new JSONArray(body); // переделываем в JSON
                    JSONObject object = new JSONObject(arr.getString(0));

                    assertEquals(object.get("id"), 1);
                    assertEquals(object.get("name"), "Smartphone");
                })
                .andReturn();
    }

    @Test
    void find() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/category/1"))
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
        this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8090/api/category/name_Smartphone"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    String body = mvcResult.getResponse().getContentAsString();

                    JSONArray arr = new JSONArray(body);
                    JSONObject object = new JSONObject(arr.getString(0));

                    assertEquals(object.get("name"), "Smartphone");
                })
                .andReturn();
    }

    @Test
    void delete() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("http://localhost:8090/api/category/6"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(mvcResult -> {
                    assertFalse(categoryRepository.existsById(6));
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