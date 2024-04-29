package kz.madizhaksykeldi.crudshop.mapper.client;

import kz.madizhaksykeldi.crudshop.entity.entities.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    Client fetchClient(Long clientId);

    void deleteClientById(Long clientId);

    void addClient(Client client);

    void updateClient(Long clientId, Client client);

    List<Client> findAllClients();
}
