package kz.madizhaksykeldi.crudshop.dto.client;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class ClientDTO {
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String firstName;
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String lastName;
    @JsonView({ClientView.Get.class, ClientView.Post.class, ClientView.Put.class})
    String email;
}
