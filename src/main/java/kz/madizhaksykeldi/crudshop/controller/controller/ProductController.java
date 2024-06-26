package kz.madizhaksykeldi.crudshop.controller.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.madizhaksykeldi.crudshop.dto.product.ProductDTO;
import kz.madizhaksykeldi.crudshop.dto.product.ProductView;
import kz.madizhaksykeldi.crudshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "Find all Products",
            description = "Get all Product objects.",
            tags = {"get"})
    @GetMapping("/getAll")
    @JsonView(ProductView.Get.class)
    public Collection<ProductDTO> findAll() {
        log.info("Looking for all products");
        return productService.findAll();
    }

    @Operation(
            summary = "Find a Product by Id",
            description = "Get a Product object by specifying its id.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = ProductView.Get.class)
    public ProductDTO findById(
            @Parameter(description = "Product Id.", example = "1")
            @PathVariable Long id) {
        log.info("looking for product with id {}", id);
        return productService.findById(id);
    }

    @Operation(
            summary = "Find a Product by Code",
            description = "Get a Product object by specifying its code.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = ProductDTO.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/code/{code}")
    @JsonView(value = ProductView.Get.class)
    public ProductDTO findByCode(
            @Parameter(description = "Product Code", example = "PRD-00001-USBDriveCode")
            @PathVariable String code) {
        log.info("looking for product with code {}", code);
        return productService.findByCode(code);
    }

    @Operation(
            summary = "Add a Product",
            description = "Create a Product object.",
            tags = {"post"})
    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = ProductView.Get.class)
    public ProductDTO create(
            @RequestBody @JsonView(value = ProductView.Post.class) ProductDTO product) {
        log.info("Creating product: {}", product.toString());
        return productService.create(product);
    }

    @Operation(
            summary = "Update a Product by Id",
            description = "Update a Product object by specifying its id.",
            tags = {"put"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = ProductView.Get.class)
    public ProductDTO update(
            @Parameter(description = "Product Id.", example = "1")
            @PathVariable Long id,
            @RequestBody @JsonView(value = ProductView.Put.class) ProductDTO product) {
        log.info("Update Product with id: {}", id);
        return productService.update(id, product);
    }

    @Operation(
            summary = "Delete a Product by Id",
            description = "Delete a Product object by specifying its id.",
            tags = {"delete"})
    @DeleteMapping("/{id}")
    public Long deleteProduct(
            @Parameter(description = "Product Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Delete Product with id: {}", id);
        return productService.deleteById(id);
    }
}