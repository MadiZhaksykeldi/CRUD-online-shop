package kz.madizhaksykeldi.crudshop.mapper.product;

import kz.madizhaksykeldi.crudshop.dto.product.ProductDTO;
import kz.madizhaksykeldi.crudshop.entity.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO toDto(Product product) {
        if (product == null) return ProductDTO.builder().build();
        return ProductDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productCode(product.getProductCode())
                .build();
    }

    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null) return Product.builder().build();
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .productCode(productDTO.getProductCode())
                .build();
    }
}
