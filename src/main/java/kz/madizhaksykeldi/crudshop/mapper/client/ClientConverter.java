package kz.madizhaksykeldi.crudshop.mapper.client;

import kz.madizhaksykeldi.crudshop.dto.client.ClientDTO;
import kz.madizhaksykeldi.crudshop.entity.entities.Client;

import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public ClientDTO toDto(Client client) {
        return ClientDTO.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .build();
    }

    public Client toClient(ClientDTO clientDTO) {
        return Client.builder()
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .build();
    }
}
