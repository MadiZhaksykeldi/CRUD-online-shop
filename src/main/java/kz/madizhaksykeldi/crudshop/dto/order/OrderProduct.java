package kz.madizhaksykeldi.crudshop.dto.order;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Value;

@Value
public record OrderProduct(
        @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class}) String productCode,
        @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class}) int quantity) {
}
