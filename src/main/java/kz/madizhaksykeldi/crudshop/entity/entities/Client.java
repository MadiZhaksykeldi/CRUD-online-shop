package kz.madizhaksykeldi.crudshop.entity.entities;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Client {
    long clientId;
    String firstName;
    String lastName;
    String email;
}