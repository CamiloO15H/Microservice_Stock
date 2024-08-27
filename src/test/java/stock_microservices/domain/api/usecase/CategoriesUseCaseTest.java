package stock_microservices.domain.api.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import stock_microservices.domain.model.Categories;
import stock_microservices.domain.spi.CategoriesPersistencePort;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriesUseCaseTest {

    @Mock
    private CategoriesPersistencePort categoriesPersistencePort;

    @InjectMocks
    private CategoriesUseCase categoriesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategories() {
        Categories category = new Categories(1L, "Test Category", "Test Description");

        categoriesUseCase.saveCategories(category);

        verify(categoriesPersistencePort, times(1)).saveCategories(category);
        assertEquals("Test Category", category.getName());
        assertEquals("Test Description", category.getDescription());
    }

    @Test
    void testGetCategoriesByName() {
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");

        when(categoriesPersistencePort.getCategoriesByName("Electronics")).thenReturn(category);

        Categories foundCategory = categoriesUseCase.getCategoriesByName("Electronics");

        assertNotNull(foundCategory);
        assertEquals("Electronics", foundCategory.getName());
        verify(categoriesPersistencePort, times(1)).getCategoriesByName("Electronics");
    }

    @Test
    void getAllCategories() {
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");

        Pageable pageable = PageRequest.of(0, 10);
        Page<Categories> page = new PageImpl<>(Collections.singletonList(category));

        when(categoriesPersistencePort.getAllCategories(pageable)).thenReturn(page);

        Page<Categories> categoriesPage = categoriesUseCase.getAllCategories(pageable);

        assertNotNull(categoriesPage);
        assertEquals(1, categoriesPage.getTotalElements());
        verify(categoriesPersistencePort, times(1)).getAllCategories(pageable);
    }

    @Test
    void updateCategories() {
        Categories category = new Categories(1L, "Electronics", "Devices and gadgets");

        when(categoriesPersistencePort.updateCategories(category)).thenReturn(category);

        Categories updatedCategory = categoriesUseCase.updateCategories(category);

        assertNotNull(updatedCategory);
        assertEquals("Electronics", updatedCategory.getName());
        verify(categoriesPersistencePort, times(1)).updateCategories(category);
    }

    @Test
    void deleteCategories() {
        Long categoryId = 1L;

        doNothing().when(categoriesPersistencePort).deleteCategories(categoryId);

        categoriesUseCase.deleteCategories(categoryId);

        verify(categoriesPersistencePort, times(1)).deleteCategories(categoryId);
    }
}