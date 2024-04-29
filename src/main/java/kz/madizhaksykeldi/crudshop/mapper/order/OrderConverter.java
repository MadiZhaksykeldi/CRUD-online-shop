package kz.madizhaksykeldi.crudshop.mapper.order;

import kz.madizhaksykeldi.crudshop.dto.order.OrderDTO;
import kz.madizhaksykeldi.crudshop.transactions.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public Order toOrder(OrderDTO orderDTO) {
        return Order.builder()
                .clientId(orderDTO.getClientId())
                .orderStatus(orderDTO.getStatus())
                .build();
    }

    public OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .clientId(order.getClientId())
                .status(order.getOrderStatus())
                .build();
    }
}
