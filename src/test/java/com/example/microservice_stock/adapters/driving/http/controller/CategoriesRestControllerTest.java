package com.example.microservice_stock.adapters.driving.http.controller;

import com.example.microservice_stock.adapters.driving.http.dto.request.AddCategoriesRequest;
import com.example.microservice_stock.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import com.example.microservice_stock.adapters.driving.http.dto.response.CategoriesResponse;
import com.example.microservice_stock.adapters.driving.http.mapper.ICategoriesRequestMapper;
import com.example.microservice_stock.adapters.driving.http.mapper.ICategoriesResponseMapper;
import com.example.microservice_stock.domain.api.ICategoriesServicePort;
import com.example.microservice_stock.domain.model.Categories;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriesRestController.class) // Esto configura el test para un controlador específico
public class CategoriesRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoriesServicePort categoriesServicePort;

    @MockBean
    private ICategoriesRequestMapper categoriesRequestMapper;

    @MockBean
    private ICategoriesResponseMapper categoriesResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategories() throws Exception {
        AddCategoriesRequest request = new AddCategoriesRequest("Electronics", "Devices and gadgets");
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");

        // Configura el mock
        when(categoriesRequestMapper.addRequestToCategories(request)).thenReturn(category);
        doNothing().when(categoriesServicePort).saveCategories(category);

        mockMvc.perform(post("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllCategories() throws Exception {
        CategoriesResponse response = new CategoriesResponse(1L, "Electronics", "Devices and gadgets");

        when(categoriesServicePort.getAllCategories(0, 10)).thenReturn(Collections.singletonList(new Categories(1L, "Electronics", "Devices and gadgets")));
        when(categoriesResponseMapper.toCategoriesResponseList(anyList())).thenReturn(Collections.singletonList(response));

        mockMvc.perform(get("/categories/")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Electronics"))
                .andExpect(jsonPath("$[0].description").value("Devices and gadgets"));
    }


    @Test
    public void testGetCategoriesByName() throws Exception {
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");
        CategoriesResponse response = new CategoriesResponse(1L, "Electronics", "Devices and gadgets");

        when(categoriesServicePort.getCategoriesByName("Electronics")).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(category)).thenReturn(response);

        mockMvc.perform(get("/categories/search/Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"));

        verify(categoriesServicePort, times(1)).getCategoriesByName("Electronics");
    }


    @Test
    public void testUpdateCategories() throws Exception {
        // Preparar datos de prueba
        UpdateCategoriesRequest request = new UpdateCategoriesRequest(1L, "Electronics", "Devices and gadgets");
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");
        CategoriesResponse response = new CategoriesResponse(1L, "Electronics", "Devices and gadgets");

        // Configurar mocks
        when(categoriesRequestMapper.updateRequestToCategories(any(UpdateCategoriesRequest.class))).thenReturn(category);
        when(categoriesServicePort.updateCategories(any(Categories.class))).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(any(Categories.class))).thenReturn(response);

        // Ejecutar y verificar
        mockMvc.perform(put("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"))
                .andExpect(jsonPath("$.description").value("Devices and gadgets"))
                .andExpect(result -> {
                    // Imprimir el contenido de la respuesta para depuración
                    System.out.println(result.getResponse().getContentAsString());
                });
    }


    @Test
    public void testDeleteCategories() throws Exception {
        doNothing().when(categoriesServicePort).deleteCategories(1L);

        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isNoContent());

        verify(categoriesServicePort, times(1)).deleteCategories(1L);
    }

    // Método auxiliar para convertir un objeto a JSON
    private String asJsonString(final Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
