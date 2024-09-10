package stock_microservices.domain.api.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.service.BrandService;
import stock_microservices.adapters.driving.http.utils.JsonParser;
import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.exceptions.OutOfBoundsException;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.spi.BrandPersistencePort;
import stock_microservices.domain.utils.DomainConstants;
import stock_microservices.domain.utils.pagination.DomainPage;
import stock_microservices.domain.utils.pagination.PaginationData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void save() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        doThrow(new EntityNotFoundException("Brand with name nothing not found"))
                .when(brandPersistencePort).getBrandByName("nothing");
        doNothing().when(brandPersistencePort).save(brand);
        brandUseCase.save(brand);
        verify(brandPersistencePort).save(brand);
    }

    @Test
    void saveRepeatedName() {
        Brand brand = new Brand(1L, "nothing", "description", null);
        when(brandPersistencePort.getBrandByName("nothing")).thenReturn(brand);
        doNothing().when(brandPersistencePort).save(brand);
        assertThrows(EntityAlreadyExistsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveNotName() {
        Brand brand = new Brand(1L, null, "description", null);
        assertThrows(EmptyFieldException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveNotDescription() {
        Brand brand = new Brand(1L, "name", null, null);
        assertThrows(EmptyFieldException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveTooLargeName() {
        Brand brand = new Brand(1L, "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn", "description", null);
        assertThrows(OutOfBoundsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void saveTooLargeDescription() {
        Brand brand = new Brand(1L,
                "nothing",
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
                null);
        assertThrows(OutOfBoundsException.class, () -> brandUseCase.save(brand));
        verify(brandPersistencePort, times(0)).save(brand);
    }

    @Test
    void getAllBrands() {
        PaginationData paginationData = new PaginationData(0, null, true);
        DomainPage<Brand> mockBrands = new DomainPage<>();
        mockBrands.setContent(List.of(
                new Brand(1L, "nothing", "description", null),
                new Brand(2L, "something", "second description", null)
        ));
        when(brandPersistencePort.getAllBrands(paginationData)).thenReturn(mockBrands);
        DomainPage<Brand> returnedBrands = brandUseCase.getAllBrands(paginationData);
        verify(brandPersistencePort).getAllBrands(paginationData);
        assertEquals(mockBrands.getContent().size(), returnedBrands.getContent().size());
        assertEquals(mockBrands.getContent().get(0).getId(), returnedBrands.getContent().get(0).getId());
        assertEquals(mockBrands.getContent().get(1).getId(), returnedBrands.getContent().get(1).getId());
    }
}