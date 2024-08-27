package stock_microservices.adapters.driving.http.controller;

import org.springframework.data.domain.*;
import stock_microservices.adapters.driving.http.dto.request.AddCategoriesRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoriesResponse;
import stock_microservices.adapters.driving.http.mapper.CategoriesRequestMapper;
import stock_microservices.adapters.driving.http.mapper.CategoriesResponseMapper;
import stock_microservices.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import stock_microservices.domain.api.CategoriesServicePort;
import stock_microservices.domain.model.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoriesRestControllerAdapter {

    // Inyección de dependencias para el servicio de categorías y los mapeadores de solicitudes y respuestas
    private final CategoriesServicePort categoriesServicePort;
    private final CategoriesRequestMapper categoriesRequestMapper;
    private final CategoriesResponseMapper categoriesResponseMapper;


    // Endpoint para crear una nueva categoría
    @PostMapping("/")
    public ResponseEntity<CategoriesResponse> saveCategories(@RequestBody AddCategoriesRequest request) {
        Categories category = categoriesRequestMapper.addRequestToCategories(request);
        Categories savedCategory = categoriesServicePort.saveCategories(category);
        CategoriesResponse response = categoriesResponseMapper.toCategoriesResponse(savedCategory);
        return ResponseEntity.created(URI.create("/categories/" + savedCategory.getId())).body(response);
    }

    // Endpoint para obtener todas las categorías con paginación y ordenamiento
    @GetMapping("/")
    public ResponseEntity<Page<CategoriesResponse>> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size,
                                                                     @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, direction, "name");
        Page<Categories> categoriesPage = categoriesServicePort.getAllCategories(pageable);

        if (categoriesPage == null || categoriesPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        }


        List<CategoriesResponse> responseList = categoriesResponseMapper.toCategoriesResponseList(categoriesPage.getContent());
        Page<CategoriesResponse> responsePage = new PageImpl<>(responseList, pageable, categoriesPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    // Endpoint para buscar una categoría por nombre
    @GetMapping("/search/{name}")
    public ResponseEntity<CategoriesResponse> getCategoriesByName(@PathVariable String name) {
        Categories categories = categoriesServicePort.getCategoriesByName(name);
        return ResponseEntity.ok(categoriesResponseMapper.toCategoriesResponse(categories));
    }

    // Endpoint para actualizar una categoría existente
    @PutMapping("/")
    public ResponseEntity<CategoriesResponse> updateCategories(@RequestBody UpdateCategoriesRequest request) {
        return ResponseEntity.ok(categoriesResponseMapper.toCategoriesResponse(
                categoriesServicePort.updateCategories(categoriesRequestMapper.updateRequestToCategories(request))
        ));
    }

    // Endpoint para eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategories(@PathVariable Long id) {
        categoriesServicePort.deleteCategories(id);
        return ResponseEntity.noContent().build();
    }
}