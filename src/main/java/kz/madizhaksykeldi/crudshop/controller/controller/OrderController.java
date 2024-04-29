package kz.madizhaksykeldi.crudshop.controller.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.madizhaksykeldi.crudshop.dto.order.OrderDTO;
import kz.madizhaksykeldi.crudshop.dto.order.OrderView;
import kz.madizhaksykeldi.crudshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Find all Orders",
            description = "Get all Order objects.",
            tags = {"get"})
    @GetMapping("/getAll")
    @JsonView(OrderView.Get.class)
    public Collection<OrderDTO> findAll() {
        log.info("Looking for all orders");
        return orderService.findAll();
    }

    @Operation(
            summary = "Find an Order by Id",
            description = "Get an Order object by specifying its id.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = OrderDTO.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = OrderView.Get.class)
    public OrderDTO findById(
            @Parameter(description = "Order Id.", example = "1")
            @PathVariable Long id) {
        log.info("looking for order with id {}", id);
        return orderService.findById(id);
    }

    @Operation(
            summary = "Create a new Order",
            description = "Create a new Order.",
            tags = {"post"})
    @PostMapping("/addOrder")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = OrderView.Post.class)
    public int create(
            @RequestBody @JsonView(value = OrderView.Post.class) OrderDTO orderDTO) {
        log.info("Creating order: {}", orderDTO.toString());
        return orderService.create(orderDTO);
    }

    @Operation(
            summary = "Update an Order by Id",
            description = "Update an Order object by specifying its id.",
            tags = {"put"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = OrderView.Get.class)
    public OrderDTO update(
            @Parameter(description = "Order Id.", example = "1")
            @PathVariable Long id,
            @RequestBody @JsonView(value = OrderView.Put.class) OrderDTO orderDTO) {
        log.info("Update Order with id: {}", id);
        return orderService.update(id, orderDTO);
    }

    @Operation(
            summary = "Delete an Order by Id",
            description = "Delete an Order object by specifying its id.",
            tags = {"delete"})
    @DeleteMapping("/{id}")
    public Long deleteOrder(
            @Parameter(description = "Order Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Delete Order with id: {}", id);
        return orderService.deleteById(id);
    }
}