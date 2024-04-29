package kz.madizhaksykeldi.crudshop.dto.order;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderDTO {
    @JsonView({OrderView.Get.class, OrderView.Post.class})
    int clientId;
    @JsonView({OrderView.Get.class, OrderView.Post.class})
    List<OrderProduct> products;
    @JsonView({OrderView.Get.class, OrderView.Post.class, OrderView.Put.class})
    String status;
}