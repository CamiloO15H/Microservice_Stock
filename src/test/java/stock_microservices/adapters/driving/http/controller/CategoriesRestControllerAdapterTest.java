package stock_microservices.adapters.driving.http.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import stock_microservices.adapters.driving.http.dto.request.AddCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoriesResponse;
import stock_microservices.adapters.driving.http.mapper.CategoriesRequestMapper;
import stock_microservices.adapters.driving.http.mapper.CategoriesResponseMapper;
import stock_microservices.domain.api.CategoriesServicePort;
import stock_microservices.domain.model.Categories;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoriesRestControllerAdapterTest {

    @Mock
    private CategoriesServicePort categoriesServicePort;

    @Mock
    private CategoriesRequestMapper categoriesRequestMapper;

    @Mock
    private CategoriesResponseMapper categoriesResponseMapper;

    @InjectMocks
    private CategoriesRestControllerAdapter categoriesRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategories() {
        AddCategoriesRequest request = new AddCategoriesRequest("Test Category", "Test Description");
        Categories category = new Categories(1L, "Test Category", "Test Description");
        CategoriesResponse response = new CategoriesResponse(1L, "Test Category", "Test Description");

        when(categoriesRequestMapper.addRequestToCategories(any(AddCategoriesRequest.class))).thenReturn(category);
        when(categoriesServicePort.saveCategories(any(Categories.class))).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(any(Categories.class))).thenReturn(response);

        ResponseEntity<CategoriesResponse> result = categoriesRestControllerAdapter.saveCategories(request);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(URI.create("/categories/1"), result.getHeaders().getLocation());
        assertEquals(response, result.getBody());
        verify(categoriesServicePort, times(1)).saveCategories(any(Categories.class));
    }

    @Test
    void getAllCategories() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");
        Page<Categories> categoriesPage = new PageImpl<>(Collections.singletonList(new Categories(1L, "Test Category", "Test Description")));
        List<CategoriesResponse> responseList = Collections.singletonList(new CategoriesResponse(1L, "Test Category", "Test Description"));
        Page<CategoriesResponse> responsePage = new PageImpl<>(responseList, pageable, categoriesPage.getTotalElements());

        when(categoriesServicePort.getAllCategories(pageable)).thenReturn(categoriesPage);
        when(categoriesResponseMapper.toCategoriesResponseList(anyList())).thenReturn(responseList);

        ResponseEntity<Page<CategoriesResponse>> result = categoriesRestControllerAdapter.getAllCategories(0, 10, "asc");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(responsePage, result.getBody());
        verify(categoriesServicePort, times(1)).getAllCategories(pageable);
    }

    @Test
    void getCategoriesByName() {
        Categories category = new Categories(1L, "Test Category", "Test Description");
        CategoriesResponse response = new CategoriesResponse(1L, "Test Category", "Test Description");

        when(categoriesServicePort.getCategoriesByName("Test Category")).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(any(Categories.class))).thenReturn(response);

        ResponseEntity<CategoriesResponse> result = categoriesRestControllerAdapter.getCategoriesByName("Test Category");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(categoriesServicePort, times(1)).getCategoriesByName("Test Category");
    }

    @Test
    void updateCategories() {
        UpdateCategoriesRequest request = new UpdateCategoriesRequest(1L, "Updated Category", "Updated Description");
        Categories category = new Categories(1L, "Updated Category", "Updated Description");
        CategoriesResponse response = new CategoriesResponse(1L, "Updated Category", "Updated Description");

        when(categoriesRequestMapper.updateRequestToCategories(any(UpdateCategoriesRequest.class))).thenReturn(category);
        when(categoriesServicePort.updateCategories(any(Categories.class))).thenReturn(category);
        when(categoriesResponseMapper.toCategoriesResponse(any(Categories.class))).thenReturn(response);

        ResponseEntity<CategoriesResponse> result = categoriesRestControllerAdapter.updateCategories(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(categoriesServicePort, times(1)).updateCategories(any(Categories.class));
    }

    @Test
    void deleteCategories() {
        doNothing().when(categoriesServicePort).deleteCategories(1L);

        ResponseEntity<Void> result = categoriesRestControllerAdapter.deleteCategories(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(categoriesServicePort, times(1)).deleteCategories(1L);
    }
}