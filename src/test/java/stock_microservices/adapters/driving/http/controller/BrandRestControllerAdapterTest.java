package stock_microservices.adapters.driving.http.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import stock_microservices.adapters.driving.http.dto.request.AddBrandRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateBrandRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.mapper.BrandRequestMapper;
import stock_microservices.adapters.driving.http.mapper.BrandResponseMapper;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.model.Brand;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BrandRestControllerAdapterTest {

    @Mock
    private BrandServicePort brandServicePort;

    @Mock
    private BrandRequestMapper brandRequestMapper;

    @Mock
    private BrandResponseMapper brandResponseMapper;

    @InjectMocks
    private BrandRestControllerAdapter brandRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrand() {
        AddBrandRequest request = new AddBrandRequest("Test Brand", "Test Description");
        Brand brand = new Brand(1L, "Test Brand", "Test Description");
        BrandResponse response = new BrandResponse(1L, "Test Brand", "Test Description");

        when(brandRequestMapper.addRequestToBrand(any(AddBrandRequest.class))).thenReturn(brand);
        when(brandServicePort.createBrand(any(Brand.class))).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(any(Brand.class))).thenReturn(response);

        ResponseEntity<BrandResponse> result = brandRestControllerAdapter.createBrand(request);

        assertEquals(201, result.getStatusCodeValue());
        assertEquals(URI.create("/brand/1"), result.getHeaders().getLocation());
        assertEquals(response, result.getBody());
        verify(brandServicePort, times(1)).createBrand(any(Brand.class));
    }

    @Test
    void getAllBrand() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.ASC, "name");
        Page<Brand> brandPage = new PageImpl<>(Collections.singletonList(new Brand(1L, "Test Brand", "Test Description")));
        List<BrandResponse> responseList = Collections.singletonList(new BrandResponse(1L, "Test Brand", "Test Description"));
        Page<BrandResponse> responsePage = new PageImpl<>(responseList, pageable, brandPage.getTotalElements());

        when(brandServicePort.getAllBrand(pageable)).thenReturn(brandPage);
        when(brandResponseMapper.toBrandResponseList(anyList())).thenReturn(responseList);

        ResponseEntity<Page<BrandResponse>> result = brandRestControllerAdapter.getAllBrand(0, 10, "asc");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(responsePage, result.getBody());
        verify(brandServicePort, times(1)).getAllBrand(pageable);
    }

    @Test
    void getBrandByName() {
        Brand brand = new Brand(1L, "Test Brand", "Test Description");
        BrandResponse response = new BrandResponse(1L, "Test Brand", "Test Description");

        when(brandServicePort.getBrandByName("Test Brand")).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(any(Brand.class))).thenReturn(response);

        ResponseEntity<BrandResponse> result = brandRestControllerAdapter.getBrandByName("Test Brand");

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(brandServicePort, times(1)).getBrandByName("Test Brand");
    }

    @Test
    void updateBrand() {
        UpdateBrandRequest request = new UpdateBrandRequest(1L, "Updated Brand", "Updated Description");
        Brand brand = new Brand(1L, "Updated Brand", "Updated Description");
        BrandResponse response = new BrandResponse(1L, "Updated Brand", "Updated Description");

        when(brandRequestMapper.updateRequestToBrand(any(UpdateBrandRequest.class))).thenReturn(brand);
        when(brandServicePort.updateBrand(any(Brand.class))).thenReturn(brand);
        when(brandResponseMapper.toBrandResponse(any(Brand.class))).thenReturn(response);

        ResponseEntity<BrandResponse> result = brandRestControllerAdapter.updateBrand(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(brandServicePort, times(1)).updateBrand(any(Brand.class));
    }

    @Test
    void deleteBrand() {
        doNothing().when(brandServicePort).deleteBrand(1L);

        ResponseEntity<Void> result = brandRestControllerAdapter.deleteBrand(1L);

        assertEquals(204, result.getStatusCodeValue());
        verify(brandServicePort, times(1)).deleteBrand(1L);
    }
}