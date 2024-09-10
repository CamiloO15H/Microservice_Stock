package stock_microservices.domain.api.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.exceptions.OutOfBoundsException;
import stock_microservices.domain.model.Category;
import stock_microservices.domain.spi.CategoryPersistencePort;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {

    @Mock
    private CategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Category category = new Category(1L, "nothing", "description", null);
        doThrow(new EntityNotFoundException("Category with name nothing not found"))
                .when(categoryPersistencePort).getCategoryByName("nothing");
        doNothing().when(categoryPersistencePort).save(category);
        categoryUseCase.save(category);
        verify(categoryPersistencePort).save(category);
    }

    @Test
    void saveRepeatedName() {
        Category category = new Category(1L, "nothing", "description", null);
        when(categoryPersistencePort.getCategoryByName("nothing")).thenReturn(category);
        doNothing().when(categoryPersistencePort).save(category);
        assertThrows(EntityAlreadyExistsException.class, () -> categoryUseCase.save(category));
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveNotName() {
        Category category = new Category(1L, "", "description", null);
        assertThrows(EmptyFieldException.class, () -> categoryUseCase.save(category));
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveNotDescription() {
        Category category = new Category(1L, "name", "", null);
        assertThrows(EmptyFieldException.class, () -> categoryUseCase.save(category));
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveTooLargeName() {
        Category category = new Category(1L, "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "description", null);
        assertThrows(OutOfBoundsException.class, () -> categoryUseCase.save(category));
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void saveTooLargeDescription() {
        Category category = new Category(1L, "nothing", "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb", null);
        assertThrows(OutOfBoundsException.class, () -> categoryUseCase.save(category));
        verify(categoryPersistencePort, times(0)).save(category);
    }

    @Test
    void getCategory() {
        Category category = new Category(1L, "nothing", "description", null);
        when(categoryPersistencePort.getCategory(1L)).thenReturn(category);
        Category returnedCategory = categoryUseCase.getCategory(1L);
        verify(categoryPersistencePort).getCategory(1L);
        assertEquals(category.getId(), returnedCategory.getId());
        assertEquals(category.getName(), returnedCategory.getName());
    }

    @Test
    void getAllCategories() {
        PaginationData paginationData = new PaginationData(0, null, true);
        DomainPage<Category> mockCategories = new DomainPage<>();
        mockCategories.setContent(List.of(
                new Category(1L, "nothing", "description", null),
                new Category(2L, "something", "second description", null)
        ));
        when(categoryPersistencePort.getAllCategories(paginationData)).thenReturn(mockCategories);
        DomainPage<Category> returnedCategories = categoryUseCase.getAllCategories(paginationData);
        verify(categoryPersistencePort).getAllCategories(paginationData);
        assertEquals(mockCategories.getContent().size(), returnedCategories.getContent().size());
        assertEquals(mockCategories.getContent().get(0).getId(), returnedCategories.getContent().get(0).getId());
        assertEquals(mockCategories.getContent().get(1).getId(), returnedCategories.getContent().get(1).getId());
    }
}