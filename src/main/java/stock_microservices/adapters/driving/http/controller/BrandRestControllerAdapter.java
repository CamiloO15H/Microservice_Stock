package stock_microservices.adapters.driving.http.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/brand") // Ruta base para el controlador
@RequiredArgsConstructor
public class BrandRestControllerAdapter {

    private final BrandService brandService;

    @Operation(summary = "Add a new brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand added", content = @Content),
            @ApiResponse(responseCode = "409", description = "Brand already exists", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand name is too long", content = @Content),
            @ApiResponse(responseCode = "400", description = "Brand description is too long", content = @Content)
    })
    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest) {
        brandService.save(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Gets all brands, they are paged, if you want it, you can sort by name or description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A list of the found brands", content = @Content),
    })
    @GetMapping
    public ResponseEntity<PageResponse<BrandResponse>> getAll(@RequestParam Map<String, String> query) {
        PageResponse<BrandResponse> foundBrands;
        PaginationRequest paginationRequest = new PaginationRequest(query);
        foundBrands = brandService.getAllBrands(paginationRequest);
        return ResponseEntity.ok(foundBrands);
    }
}
