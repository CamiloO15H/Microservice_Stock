package stock_microservices.domain.api.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import stock_microservices.domain.model.Article;
import stock_microservices.domain.model.Brand;
import stock_microservices.domain.model.Categories;
import stock_microservices.domain.spi.ArticlePersistencePort;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ArticleUseCaseTest {

    @Mock
    private ArticlePersistencePort articlePersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateArticle_Success() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));
        Article article = new Article(
                null, "Laptop", "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Configurar el mock para que devuelva el artículo
        doNothing().when(articlePersistencePort).createArticle(article);

        // Ejecutar el método de prueba
        articleUseCase.createArticle(article);

        // Verificar los resultados
        verify(articlePersistencePort, times(1)).createArticle(article);
    }

    @Test
    public void testCreateArticle_ValidationFailure_NullName() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));

        // Artículo con nombre nulo
        Article invalidArticle = new Article(
                null, null, "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(invalidArticle);
        });

        assertEquals("Article name cannot be null or blank", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_BlankName() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));

        // Artículo con nombre en blanco
        Article invalidArticle = new Article(
                1L," ", "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(invalidArticle);
        });

        assertEquals("Article name cannot be null or blank", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_NullDescription() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));

        // Artículo con descripción nula
        Article invalidArticle = new Article(
                1L, "Laptop", null, 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(invalidArticle);
        });

        assertEquals("Article description cannot be null or blank", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_BlankDescription() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));

        // Artículo con descripción en blanco
        Article invalidArticle = new Article(
                1L,"Laptop", " ", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(invalidArticle);
        });

        assertEquals("Article description cannot be null or blank", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_NoCategories() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();

        // Artículo sin categorías
        Article invalidArticle = new Article(
                1L, "Laptop", "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(invalidArticle);
        });

        assertEquals("Article must have at least one category", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_TooManyCategories() {
        // Preparar datos de prueba
        Set<Categories> categories = new HashSet<>();
        categories.add(new Categories(1L, "Electronics", "Electronic items"));
        categories.add(new Categories(2L, "Appliances", "Home appliances"));
        categories.add(new Categories(3L, "Furniture", "Furniture items"));
        categories.add(new Categories(4L, "Clothing", "Clothing items")); // Más de 3 categorías

        Article article = new Article(
                1L,"Laptop", "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals("Article cannot have more than three categories", thrown.getMessage());
    }

    @Test
    public void testCreateArticle_ValidationFailure_DuplicateCategories() {
        // Preparar datos de prueba con instancias diferentes pero con el mismo ID
        Set<Categories> categories = new HashSet<>();
        Categories category1 = new Categories(1L, "Electronics", "Electronic items");
        Categories category2 = new Categories(1L, "Electronics", "Electronic items"); // Misma categoría en términos de ID

        categories.add(category1);
        categories.add(category2); // Agregar categorías duplicadas

        Article article = new Article(
                1L, "Laptop", "High performance laptop", 10, 1200.00, new Brand(1L, "BrandX", "Description"), categories
        );

        // Imprimir el tamaño de categorías
        System.out.println("Article Categories Size: " + article.getCategories().size());

        // Ejecutar y verificar la validación
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            articleUseCase.createArticle(article);
        });

        assertEquals("Article cannot have duplicate categories.", thrown.getMessage());
    }



}