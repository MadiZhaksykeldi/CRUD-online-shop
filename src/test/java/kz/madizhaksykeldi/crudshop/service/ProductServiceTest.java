//package kz.madizhaksykeldi.crudshop.service;
//
//import kz.madizhaksykeldi.crudshop.dto.product.ProductDTO;
//import kz.madizhaksykeldi.crudshop.entity.entities.Product;
//import kz.madizhaksykeldi.crudshop.mapper.product.ProductConverter;
//import kz.madizhaksykeldi.crudshop.mapper.product.ProductMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//class ProductServiceTest {
//
//    private ProductService productService;
//    private ProductMapper productMapper;
//    private ProductConverter productConverter;
//
//    @BeforeEach
//    public void setUp() {
//        productMapper = mock(ProductMapper.class);
//        productConverter = mock(ProductConverter.class);
//        productService = new ProductService(productMapper, productConverter);
//    }
//
//    @Test
//    void testFindAll() {
//        // Given
//        List<Product> products = Arrays.asList(Product.builder().build(), Product.builder().build());
//        List<ProductDTO> expectedProductDtos = Arrays.asList(ProductDTO.builder().build(), ProductDTO.builder().build());
//
//        when(productMapper.findAllProducts()).thenReturn(products);
//        when(productConverter.toDto(any(Product.class))).thenReturn(expectedProductDtos.get(0), expectedProductDtos.get(1));
//
//        // When
//        List<ProductDTO> result = productService.findAll();
//
//        // Then
//        assertThat(result).isEqualTo(expectedProductDtos);
//    }
//
//    @Test
//    void testFindById() {
//        // Given
//        Long id = 1L;
//        Product product = Product.builder().build();
//        ProductDTO expectedProductDto = ProductDTO.builder().build();
//
//        when(productMapper.fetchProductById(id)).thenReturn(product);
//        when(productConverter.toDto(any(Product.class))).thenReturn(expectedProductDto);
//
//        // When
//        ProductDTO result = productService.findById(id);
//
//        // Then
//        assertThat(result).isEqualTo(expectedProductDto);
//    }
//
//    @Test
//    void testFindByCode() {
//        // Given
//        String code = "ABC123";
//
//        when(productMapper.fetchProductByCode(code)).thenThrow(new RuntimeException("Product not found"));
//
//        // When / Then
//        assertThrows(RuntimeException.class, () -> productService.findByCode(code));
//    }
//
//
//    @Test
//    void findAll_ShouldReturnListOfProducts() {
//        // Arrange
//        List<Product> mockProducts = Collections.singletonList(Product.builder().build());
//        List<ProductDTO> expectedProductDtos = Collections.singletonList(ProductDTO.builder().build());
//
//        when(productMapper.findAllProducts()).thenReturn(mockProducts);
//        when(productConverter.toDto(any())).thenReturn(ProductDTO.builder().build());
//
//        // Act
//        List<ProductDTO> result = productService.findAll();
//
//        // Convert both lists to ArrayList for proper comparison
//        List<ProductDTO> expectedProductDtosList = new ArrayList<>(expectedProductDtos);
//        List<ProductDTO> resultList = new ArrayList<>(result);
//
//        // Assert
//        assertEquals(expectedProductDtosList, resultList);
//        verify(productMapper, times(1)).findAllProducts();
//        verify(productConverter, times(1)).toDto(any());
//    }
//
//    @Test
//    void findById_ShouldReturnProductDto() {
//        // Arrange
//        long productId = 1L;
//        Product mockProduct = Product.builder().build();
//        ProductDTO expectedProductDto = ProductDTO.builder().build();
//
//        when(productMapper.fetchProductById(productId)).thenReturn(mockProduct);
//        when(productConverter.toDto(mockProduct)).thenReturn(expectedProductDto);
//
//        // Act
//        ProductDTO result = productService.findById(productId);
//
//        // Assert
//        assertEquals(expectedProductDto, result);
//        verify(productMapper, times(1)).fetchProductById(productId);
//        verify(productConverter, times(1)).toDto(mockProduct);
//    }
//
//    @Test
//    void findByCode_ShouldReturnProductDto() {
//        // Arrange
//        String productCode = "ABC123";
//        Product mockProduct = Product.builder().build();
//        ProductDTO expectedProductDto = ProductDTO.builder().build();
//
//        when(productMapper.fetchProductByCode(productCode)).thenReturn(mockProduct);
//        when(productConverter.toDto(mockProduct)).thenReturn(expectedProductDto);
//
//        // Act
//        ProductDTO result = productService.findByCode(productCode);
//
//        // Assert
//        assertEquals(expectedProductDto, result);
//        verify(productMapper, times(1)).fetchProductByCode(productCode);
//        verify(productConverter, times(1)).toDto(mockProduct);
//    }
//
//    @Test
//    void create_ShouldReturnCreatedProductDto() {
//        // Arrange
//        ProductDTO inputProductDto = ProductDTO.builder().build();
//        Product createdProduct = Product.builder().build();
//        ProductDTO expectedProductDto = ProductDTO.builder().build();
//
//        when(productConverter.toProduct(inputProductDto)).thenReturn(createdProduct);
//        when(productConverter.toDto(createdProduct)).thenReturn(expectedProductDto);
//
//        // Act
//        ProductDTO result = productService.create(inputProductDto);
//
//        // Assert
//        assertEquals(expectedProductDto, result);
//        verify(productConverter, times(1)).toProduct(inputProductDto);
//        verify(productMapper, times(1)).addProduct(createdProduct);
//        verify(productConverter, times(1)).toDto(createdProduct);
//    }
//
//    @Test
//    void update_ShouldReturnUpdatedProductDto() {
//        // Arrange
//        long productId = 1L;
//        ProductDTO inputProductDto = ProductDTO.builder().build();
//        Product updatedProduct = Product.builder().build();
//        ProductDTO expectedProductDto = ProductDTO.builder().build();
//
//        when(productConverter.toProduct(inputProductDto)).thenReturn(updatedProduct);
//        when(productConverter.toDto(updatedProduct)).thenReturn(expectedProductDto);
//
//        // Act
//        ProductDTO result = productService.update(productId, inputProductDto);
//
//        // Assert
//        assertEquals(expectedProductDto, result);
//        verify(productConverter, times(1)).toProduct(inputProductDto);
//        verify(productMapper, times(1)).updateProduct(productId, updatedProduct);
//        verify(productConverter, times(1)).toDto(updatedProduct);
//    }
//
//    @Test
//    void deleteById_ShouldReturnDeletedProductId() {
//        // Arrange
//        long productId = 1L;
//
//        // Act
//        Long result = productService.deleteById(productId);
//
//        // Assert
//        assertEquals(productId, result);
//        verify(productMapper, times(1)).deleteProductById(productId);
//    }
//
//    @Test
//    void testDeleteById() {
//        // Given
//        Long id = 1L;
//
//        // When
//        Long result = productService.deleteById(id);
//
//        // Then
//        assertThat(result).isEqualTo(id);
//        verify(productMapper).deleteProductById(id);
//    }
//}
//
