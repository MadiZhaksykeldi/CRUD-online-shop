package kz.madizhaksykeldi.crudshop.service;

import kz.madizhaksykeldi.crudshop.dto.client.ClientDTO;
import kz.madizhaksykeldi.crudshop.entity.entities.Client;
import kz.madizhaksykeldi.crudshop.mapper.client.ClientConverter;
import kz.madizhaksykeldi.crudshop.mapper.client.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientConverter clientConverter;

    public List<ClientDTO> findAll() {
        return clientMapper.findAllClients().stream()
                .map(clientConverter::toDto)
                .toList();
    }

    public ClientDTO findById(Long id) {
        return clientConverter.toDto(clientMapper.fetchClient(id));
    }

    public ClientDTO create(ClientDTO clientDTO) {
        clientMapper.addClient(clientConverter.toClient(clientDTO));
        return clientDTO;
    }

    public ClientDTO update(Long id, Client client) {
        clientMapper.updateClient(id, client);
        return clientConverter.toDto(client);
    }

    public Long deleteById(Long id) {
        clientMapper.deleteClientById(id);
        return id;
    }
}
