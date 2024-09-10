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
import stock_microservices.adapters.driving.http.dto.request.BrandRequest;
import stock_microservices.adapters.driving.http.dto.request.PaginationRequest;
import stock_microservices.adapters.driving.http.dto.response.BrandResponse;
import stock_microservices.adapters.driving.http.dto.response.PageResponse;
import stock_microservices.adapters.driving.http.service.BrandService;
import stock_microservices.adapters.driving.http.utils.JsonParser;
import stock_microservices.domain.exceptions.EmptyFieldException;
import stock_microservices.domain.exceptions.EntityAlreadyExistsException;
import stock_microservices.domain.exceptions.OutOfBoundsException;
import stock_microservices.domain.utils.DomainConstants;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BrandRestControllerAdapter.class)
@AutoConfigureMockMvc
class BrandRestControllerAdapterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBrandIsOK() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("Nothing is a brand associated with nothing").build();

        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isCreated());
        verify(brandService, times(1)).save(brandMock);
    }

    @Test
    void createBrandIsRepeated() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("brand with a name with more than 50 chars").build();

        doThrow(new EntityAlreadyExistsException("entity", "nothing")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isConflict());
    }

    @Test
    void createBrandNameTooLarge() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").description("brand with a name with more than 50 chars").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.NAME.toString(), String.valueOf(DomainConstants.NAME_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandDescriptionTooLarge() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("long description").description("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee").build();

        doThrow(new OutOfBoundsException(String.join(" ", new String[]{DomainConstants.Field.DESCRIPTION.toString(), String.valueOf(DomainConstants.CATEGORY_DESCRIPTION_LENGTH_LIMIT), DomainConstants.CHARS_LIMIT_REACHED_MESSAGE}))).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandNoName() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("").description("Nothing is a brand associated with nothing").build();

        doThrow(new EmptyFieldException("NAME")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBrandNoDescription() throws Exception {
        BrandRequest brandMock = BrandRequest.builder().name("nothing").description("").build();

        doThrow(new EmptyFieldException("DESCRIPTION")).when(brandService).save(brandMock);
        this.mockMvc.perform(post("/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonParser.toJson(brandMock)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAll() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, null, true);
        PageResponse<BrandResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));
        when(brandService.getAllBrands(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/brand"))
                .andExpect(status().isOk());
    }

    @Test
    void getAllWithParams() throws Exception {
        PaginationRequest paginationRequest = new PaginationRequest(0, "name", true);
        PageResponse<BrandResponse> mockDTOs = new PageResponse<>();
        mockDTOs.setContent(List.of(
                new BrandResponse(1L, "nothing", "description"),
                new BrandResponse(2L, "something", "second description")
        ));
        when(brandService.getAllBrands(paginationRequest)).thenReturn(mockDTOs);
        this.mockMvc.perform(get("/brand")
                        .queryParam("page", "0")
                        .queryParam("sortBy", "name")
                        .queryParam("asc", "true")
                        .queryParam("pageSize", "10"))
                .andExpect(status().isOk());
    }
}