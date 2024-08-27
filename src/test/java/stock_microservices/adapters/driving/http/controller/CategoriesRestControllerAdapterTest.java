package stock_microservices.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import stock_microservices.adapters.driving.http.dto.request.AddCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoriesResponse;
import stock_microservices.adapters.driving.http.mapper.CategoriesRequestMapper;
import stock_microservices.adapters.driving.http.mapper.CategoriesResponseMapper;
import stock_microservices.domain.api.CategoriesServicePort;
import stock_microservices.domain.model.Categories;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoriesRestControllerAdapter.class)
class CategoriesRestControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // Use the ObjectMapper provided by Spring Boot

    @MockBean
    private CategoriesServicePort categoriesServicePort;

    @MockBean
    private CategoriesRequestMapper categoriesRequestMapper;

    @MockBean
    private CategoriesResponseMapper categoriesResponseMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategories() throws Exception {
        AddCategoriesRequest request = new AddCategoriesRequest();
        request.setName("Test Category");
        request.setDescription("Test Description");

        Categories category = new Categories(1L, "Test Category", "Test Description");

        CategoriesResponse response = new CategoriesResponse(1L, "Test Category", "Test Description");

        when(categoriesRequestMapper.addRequestToCategories(request)).thenReturn(category);
        when(categoriesServicePort.saveCategories(category)).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(category)).thenReturn(response);

        mockMvc.perform(post("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Category"))
                .andExpect(jsonPath("$.description").value("Test Description"));

        verify(categoriesServicePort, times(1)).saveCategories(category);
    }


    @Test
    void getAllCategories() throws Exception {
        Categories category = new Categories(1L, "Test Category", "Test Description");

        CategoriesResponse response = new CategoriesResponse(1L, "Test Category", "Test Description");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Categories> page = new PageImpl<>(Collections.singletonList(category));
        Page<CategoriesResponse> responsePage = new PageImpl<>(Collections.singletonList(response));

        when(categoriesServicePort.getAllCategories(pageable)).thenReturn(page);
        when(categoriesResponseMapper.toCategoriesResponseList(page.getContent())).thenReturn(responsePage.getContent());

        mockMvc.perform(get("/categories/")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortDirection", "asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Test Category"));

        verify(categoriesServicePort, times(1)).getAllCategories(pageable);
    }

    @Test
    void getCategoriesByName() throws Exception {
        Categories category = new Categories(1L, "Test Category", "Test Description");

        CategoriesResponse response = new CategoriesResponse(1L, "Test Category", "Test Description");

        when(categoriesServicePort.getCategoriesByName("Test Category")).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(category)).thenReturn(response);

        mockMvc.perform(get("/categories/search/{name}", "Test Category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Category"));

        verify(categoriesServicePort, times(1)).getCategoriesByName("Test Category");
    }

    @Test
    void updateCategories() throws Exception {
        UpdateCategoriesRequest request = new UpdateCategoriesRequest(1L, "Updated Category", "Updated Description");

        Categories category = new Categories(1L, "Updated Category", "Updated Description");

        CategoriesResponse response = new CategoriesResponse(1L, "Updated Category", "Updated Description");

        when(categoriesRequestMapper.updateRequestToCategories(request)).thenReturn(category);
        when(categoriesServicePort.updateCategories(category)).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(category)).thenReturn(response);

        mockMvc.perform(put("/categories/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Category"));

        verify(categoriesServicePort, times(1)).updateCategories(category);
    }

    @Test
    void deleteCategories() throws Exception {
        Long categoryId = 1L;

        doNothing().when(categoriesServicePort).deleteCategories(categoryId);

        mockMvc.perform(delete("/categories/{id}", categoryId))
                .andExpect(status().isNoContent());

        verify(categoriesServicePort, times(1)).deleteCategories(categoryId);
    }
}
