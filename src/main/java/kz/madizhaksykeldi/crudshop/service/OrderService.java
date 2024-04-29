package kz.madizhaksykeldi.crudshop.service;

import kz.madizhaksykeldi.crudshop.dto.client.ClientDTO;
import kz.madizhaksykeldi.crudshop.dto.order.OrderDTO;
import kz.madizhaksykeldi.crudshop.dto.product.ProductDTO;
import kz.madizhaksykeldi.crudshop.dto.order.OrderProduct;
import kz.madizhaksykeldi.crudshop.mapper.order.OrderConverter;
import kz.madizhaksykeldi.crudshop.mapper.order.OrderMapper;
import kz.madizhaksykeldi.crudshop.transactions.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static kz.madizhaksykeldi.crudshop.entity.dictionary.OrderStatus.CANCELED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderConverter orderConverter;
    private final Random random = new Random();

    public List<OrderDTO> findAll() {
        return orderMapper.findAllOrders().stream()
                .map(orderConverter::toDto)
                .toList();
    }

    public OrderDTO findById(Long id) {
        return orderConverter.toDto(orderMapper.fetchOrder(id));
    }

    public int create(OrderDTO orderDTO) {
        // only for test purpose, should be UUID
        final int orderId = random.nextInt();
        final int clientId = orderDTO.getClientId();
        final List<OrderProduct> allProducts = orderDTO.getProducts();

        if (!isClientPresent(clientId)) {
            throw new IllegalArgumentException("Customer not found!");
        }

        if (allProducts == null || allProducts.isEmpty() || !areProductsAvailable(allProducts)) {
            throw new IllegalArgumentException("Not all products are available");
        }

        Order order = orderConverter.toOrder(orderDTO);
        orderMapper.addOrder(order);
        orderMapper.addOrderedProducts(orderId, allProducts);
        return orderId;
    }

    public boolean isClientPresent(int clientId) {
        ClientDTO client = clientService.findById(Long.valueOf(clientId));
        return client != null;
    }

    public boolean areProductsAvailable(List<OrderProduct> allWantedProducts) {
        return allWantedProducts.stream()
                .allMatch(wantedProduct -> {
                    ProductDTO product = productService.findByCode(wantedProduct.getProductCode());

                    return product != null && product.getStockQuantity() >= wantedProduct.getQuantity();
                });
    }

    public OrderDTO update(Long id, OrderDTO orderDTO) {
        if (!isOrderExist(id)) {
            throw new IllegalArgumentException("Given order does not exist");
        }

        final String status = orderDTO.getStatus();
        orderMapper.updateOrder(id, status);
        return orderDTO;
    }

    public List<ProductDTO> cancelOrder(Long orderId) {
        Order order = orderMapper.fetchOrder(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with id " + orderId + " not found");
        }

        List<OrderProduct> orderedProducts = orderMapper.findByOrderId(orderId);

        List<ProductDTO> restoredProducts = new ArrayList<>();
        for (OrderProduct orderedProduct : orderedProducts) {
            ProductDTO product = productService.findByCode(orderedProduct.getProductCode());
            if (product != null) {
                int restoredQuantity = product.getStockQuantity() + orderedProduct.getQuantity();
                product.setStockQuantity(restoredQuantity);
                productService.updateAfterCancel(product.getProductCode(), product);
                restoredProducts.add(product);
            } else {
                throw new IllegalArgumentException("Product with code " + orderedProduct.getProductCode() + " not found");
            }
        }

        orderMapper.updateOrder(orderId, CANCELED.getReferenceName());
        return restoredProducts;
    }


    public boolean isOrderExist(Long id) {
        return orderMapper.fetchOrder(id) != null;
    }

    public Long deleteById(Long id) {
        orderMapper.deleteOrder(id);
        return id;
    }
}