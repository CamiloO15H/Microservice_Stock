package stock_microservices.adapters.driving.http.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import stock_microservices.adapters.driving.http.dto.request.CategoryRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.CategoryResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.service.CategoryService;
import stock_microservices.adapters.driving.http.utils.JsonParser;
import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.EntityNotFoundException;
import stock_microservices.domain.exceptions.OutOfBoundsException;
import stock_microservices.domain.utils.DomainConstants;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryRestControllerAdapter.class)
@AutoConfigureMockMvc
class CategoryRestControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategoryIsOK() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("nothing").description("Nothing is a category associated with nothing").build();

        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isCreated());
        verify(categoryService, times(1)).save(categoryMock);
    }

    @Test
    void createCategoryIsRepeated() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("nothing").description("Another category with the same name").build();

        doThrow(new EntityAlreadyExistsException("Category", "nothing")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isConflict());
    }

    @Test
    void createCategoryNameTooLarge() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").description("category with a name with more than 50 chars").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryDescriptionTooLarge() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("long description").description("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryNoName() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("").description("Nothing is a category associated with nothing").build();

        doThrow(new EmptyFieldException("NAME")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCategoryNoDescription() throws Exception {
        CategoryRequest categoryMock = CategoryRequest.builder().name("nothing").description("").build();

        doThrow(new EmptyFieldException("DESCRIPTION")).when(categoryService).save(categoryMock);
        this.mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(categoryMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCategoryById() throws Exception {
        CategoryResponse categoryMock = CategoryResponse.builder().id(1L).name("nothing").description("Nothing is a category associated with nothing").build();
        when(categoryService.getCategory(1L)).thenReturn(categoryMock);
        when(categoryService.getCategory(2L)).thenThrow(new EntityNotFoundException("Category with id 2 not found"));
        this.mockMvc.perform(get("/categories/1"))
                .andExpect(content().json(JsonParser.toJson(categoryMock)))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/categories/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true);
        PageResponse<CategoryResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new CategoryResponse(1L, "nothing", "description"),
                new CategoryResponse(2L, "something", "second description")
        ));
        when(categoryService.getAllCategories(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/categories"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllWithParams() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, "name", true);
        PageResponse<CategoryResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new CategoryResponse(1L, "nothing", "description"),
                new CategoryResponse(2L, "something", "second description")
        ));
        when(categoryService.getAllCategories(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/categories")
                        .queryParam("page", "0")
                        .queryParam("sortBy", "name")
                        .queryParam("pageSize", "10")
                        .queryParam("asc", "true"))
                .andExpect(status().isOk());
    }
}