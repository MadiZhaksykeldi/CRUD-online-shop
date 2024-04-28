package kz.madizhaksykeldi.crudshop.transactions;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class OrderProduct {
    int orderProductId;
    int orderId;
    int productCode;
    int quantity;
}