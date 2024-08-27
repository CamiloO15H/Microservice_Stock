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
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandUseCaseTest {

    @Mock
    private BrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveBrand() {
        Brand brand = new Brand(1L, "Test Brand", "Test Description");

        brandUseCase.createBrand(brand);

        verify(brandPersistencePort, times(1)).createBrand(brand);
        assertEquals("Test Brand", brand.getName());
        assertEquals("Test Description", brand.getDescription());
    }
    @Test
    void getAllBrand() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Brand> brandPage = new PageImpl<>(Collections.singletonList(new Brand(1L, "Test Brand", "Test Description")));

        when(brandPersistencePort.getAllBrand(pageable)).thenReturn(brandPage);

        Page<Brand> result = brandUseCase.getAllBrand(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(brandPersistencePort, times(1)).getAllBrand(pageable);
    }

    @Test
    void getBrandByName() {
        Brand brand = new Brand(1L, "Test Brand", "Test Description");

        when(brandPersistencePort.getBrandByName("Test Brand")).thenReturn(brand);

        Brand result = brandUseCase.getBrandByName("Test Brand");

        assertNotNull(result);
        assertEquals("Test Brand", result.getName());
        verify(brandPersistencePort, times(1)).getBrandByName("Test Brand");
    }

    @Test
    void updateBrand() {
        Brand brand = new Brand(1L, "Updated Brand", "Updated Description");

        when(brandPersistencePort.updateBrand(any(Brand.class))).thenReturn(brand);

        Brand updatedBrand = brandUseCase.updateBrand(brand);

        assertNotNull(updatedBrand);
        assertEquals("Updated Brand", updatedBrand.getName());
        verify(brandPersistencePort, times(1)).updateBrand(any(Brand.class));
    }

    @Test
    void deleteBrand() {
        doNothing().when(brandPersistencePort).deleteBrand(1L);

        brandUseCase.deleteBrand(1L);

        verify(brandPersistencePort, times(1)).deleteBrand(1L);
    }
}