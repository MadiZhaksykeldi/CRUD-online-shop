package kz.madizhaksykeldi.crudshop.transactions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {
    int orderId;
    int clientId;
    String orderStatus;
}