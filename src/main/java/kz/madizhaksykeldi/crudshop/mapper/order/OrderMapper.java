package kz.madizhaksykeldi.crudshop.mapper.order;

import kz.madizhaksykeldi.crudshop.dto.order.OrderProduct;
import kz.madizhaksykeldi.crudshop.transactions.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> findAllOrders();

    Order fetchOrder(Long id);

    void addOrder(Order order);

    void addOrderedProducts(int orderId, List<OrderProduct> allProducts);

    void updateOrder(Long orderId, String status);

    void deleteOrder(Long id);

    List<OrderProduct> findByOrderId(Long orderId);
}
