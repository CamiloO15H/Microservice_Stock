package stock_microservices.adapters.driving.http.controller;

import stock_microservices.adapters.driving.http.dto.request.AddBrandRequest;
import stock_microservices.adapters.driving.http.dto.request.UpdateBrandRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.mapper.BrandRequestMapper;
import stock_microservices.adapters.driving.http.mapper.BrandResponseMapper;
import stock_microservices.domain.api.BrandServicePort;
import stock_microservices.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Sort;
import java.util.List;

@RestController
@RequestMapping("/brand") // Ruta base para el controlador
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final BrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;
    private final BrandResponseMapper brandResponseMapper;

    @PostMapping("/")  // Para crear una nueva marca
    public ResponseEntity<Void> createBrand(@RequestBody AddBrandRequest request) {
        brandServicePort.createBrand(brandRequestMapper.addRequestToBrand(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")  // Para obtener todas las marcas con paginación y ordenamiento
    public ResponseEntity<List<BrandResponse>> getAllBrand(@RequestParam Integer page,
                                                            @RequestParam Integer size,
                                                            @RequestParam String sortDirection) {
        // Validar la dirección de ordenamiento
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);

        return ResponseEntity.ok(brandResponseMapper
                .toBrandResponseList(brandServicePort.getAllBrand(page, size, direction.toString())));
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
