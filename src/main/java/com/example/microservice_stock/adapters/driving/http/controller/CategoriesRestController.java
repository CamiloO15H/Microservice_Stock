package com.example.microservice_stock.adapters.driving.http.controller;

import com.example.microservice_stock.adapters.driving.http.dto.request.AddCategoriesRequest;
import com.example.microservice_stock.adapters.driving.http.dto.response.CategoriesResponse;
import com.example.microservice_stock.adapters.driving.http.mapper.ICategoriesRequestMapper;
import com.example.microservice_stock.adapters.driving.http.mapper.ICategoriesResponseMapper;
import com.example.microservice_stock.adapters.driving.http.dto.request.UpdateCategoriesRequest;
import com.example.microservice_stock.domain.api.ICategoriesServicePort;
import com.example.microservice_stock.domain.model.Categories;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/categories") //RUTE
@RequiredArgsConstructor
public class CategoriesRestController {

    private final ICategoriesServicePort categoriesServicePort;
    private final ICategoriesRequestMapper categoriesRequestMapper;
    private final ICategoriesResponseMapper categoriesResponseMapper;

    @PostMapping("/")                       //Para limpiar los puertos
    public ResponseEntity<Void> saveCategories(@RequestBody AddCategoriesRequest request) {
        categoriesServicePort.saveCategories(categoriesRequestMapper.addRequestToCategories(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoriesResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(categoriesResponseMapper.
                toCategoriesResponseList(categoriesServicePort.getAllCategories(page, size)));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<CategoriesResponse> getCategoriesByName(@PathVariable String name) {
        Categories categories = categoriesServicePort.getCategoriesByName(name);
        return ResponseEntity.ok(categoriesResponseMapper.toCategoriesResponse(categories));
    }

    @PutMapping("/")
    public ResponseEntity<CategoriesResponse> updateCategories(@RequestBody UpdateCategoriesRequest request) {
        return ResponseEntity.ok(categoriesResponseMapper.toCategoriesResponse(
                categoriesServicePort.updateCategories(categoriesRequestMapper.updateRequestToCategories(request))
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategories(@PathVariable Long id) {
        categoriesServicePort.deleteCategories(id);
        return ResponseEntity.noContent().build();
    }
}
