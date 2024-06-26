package kz.madizhaksykeldi.crudshop.mapper.product;

import kz.madizhaksykeldi.crudshop.entity.entities.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    Product fetchProductById(Long productId);

    Product fetchProductByCode(String productCode);

    void deleteProductById(Long clientId);

    void addProduct(Product product);

    void updateProduct(Long productId, Product product);

    List<Product> findAllProducts();
}
