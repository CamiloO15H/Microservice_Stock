package com.example.microservice_stock.domain.api.usecase;

import com.example.microservice_stock.domain.model.Categories;
import com.example.microservice_stock.domain.spi.ICategoriesPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriesUseCaseTest {

    @Mock
    private ICategoriesPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoriesUseCase categoriesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCategories_ShouldInvokePersistencePort() {
        Categories category = new Categories(1L, "Electronics", "Gadgets and devices");

        categoriesUseCase.saveCategories(category);

        verify(categoryPersistencePort, times(1)).saveCategories(category);
    }

    @Test
    void getCategoriesByName_ShouldReturnCategory() {
        Categories category = new Categories(1L, "Electronics", "Gadgets and devices");
        when(categoryPersistencePort.getCategoriesByName("Electronics")).thenReturn(category);

        Categories result = categoriesUseCase.getCategoriesByName("Electronics");

        assertNotNull(result);
        assertEquals("Electronics", result.getName());
        assertEquals("Gadgets and devices", result.getDescription());
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        Categories category1 = new Categories(1L, "Electronics", "Gadgets and devices");
        Categories category2 = new Categories(2L, "Books", "All kinds of books");
        List<Categories> categoriesList = Arrays.asList(category1, category2);

        when(categoryPersistencePort.getAllCategories(0, 10)).thenReturn(categoriesList);

        List<Categories> result = categoriesUseCase.getAllCategories(0, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void updateCategories_ShouldInvokePersistencePort() {
        Categories category = new Categories(1L, "Electronics", "Gadgets and devices");

        categoriesUseCase.updateCategories(category);

        verify(categoryPersistencePort, times(1)).updateCategories(category);
    }

    @Test
    void deleteCategories_ShouldInvokePersistencePort() {
        Long categoryId = 1L;

        categoriesUseCase.deleteCategories(categoryId);

        verify(categoryPersistencePort, times(1)).deleteCategories(categoryId);
    }
}
