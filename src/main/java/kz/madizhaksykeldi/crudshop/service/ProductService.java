package kz.madizhaksykeldi.crudshop.service;


import kz.madizhaksykeldi.crudshop.dto.product.ProductDTO;
import kz.madizhaksykeldi.crudshop.entity.entities.Product;
import kz.madizhaksykeldi.crudshop.mapper.product.ProductConverter;
import kz.madizhaksykeldi.crudshop.mapper.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductConverter productConverter;

    public List<ProductDTO> findAll() {
        return productMapper.findAllProducts().stream()
                .map(productConverter::toDto)
                .toList();
    }

    public ProductDTO findById(Long id) {
        return productConverter.toDto(productMapper.fetchProductById(id));
    }

    public ProductDTO findByCode(String code) {
        return productConverter.toDto(productMapper.fetchProductByCode(code));
    }

    public ProductDTO create(ProductDTO productDTO) {
        Product product = productConverter.toProduct(productDTO);
        productMapper.addProduct(product);
        return productConverter.toDto(product);
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = productConverter.toProduct(productDTO);
        productMapper.updateProduct(id, product);
        return productConverter.toDto(product);
    }

    public Long deleteById(Long id) {
        productMapper.deleteProductById(id);
        return id;
    }

    public void updateAfterCancel(String productCode, ProductDTO product) {
    }
}