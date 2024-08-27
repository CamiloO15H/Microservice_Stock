package stock_microservices.adapters.driving.http.controller;

import org.springframework.data.domain.*;
import stock_microservices.adapters.driving.http.dto.request.AddBrandRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateBrandRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.mapper.BrandRequestMapper;
import stock_microservices.adapters.driving.http.mapper.BrandResponseMapper;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/brand") // Ruta base para el controlador
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final BrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @PostMapping("/")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody AddBrandRequest request) {
        Brand brand = brandRequestMapper.addRequestToBrand(request);
        Brand savedBrand = brandServicePort.createBrand(brand);
        BrandResponse response = brandResponseMapper.toBrandResponse(savedBrand);
        return ResponseEntity.created(URI.create("/brand/" + savedBrand.getId())).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<Page<BrandResponse>> getAllBrand(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, "name");
        Page<Brand> brandsPage = brandServicePort.getAllBrand(pageable);
        List<BrandResponse> responseList = brandResponseMapper.toBrandResponseList(brandsPage.getContent());
        Page<BrandResponse> responsePage = new PageImpl<>(responseList, pageable, brandsPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/search/{name}")  // Para buscar una marca por nombre
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable String name) {
        Brand brand = brandServicePort.getBrandByName(name);
        return ResponseEntity.ok(brandResponseMapper.toBrandResponse(brand));
    }

    @PutMapping("/")  // Para actualizar una marca existente
    public ResponseEntity<BrandResponse> updateBrand(@RequestBody UpdateBrandRequest request) {
        return ResponseEntity.ok(brandResponseMapper.toBrandResponse(
                brandServicePort.updateBrand(brandRequestMapper.updateRequestToBrand(request))
        ));
    }

    @DeleteMapping("/{id}")  // Para eliminar una marca por ID
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandServicePort.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
