package kz.madizhaksykeldi.crudshop.controller.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import kz.madizhaksykeldi.crudshop.dto.client.ClientDTO;
import kz.madizhaksykeldi.crudshop.dto.client.ClientView;
import kz.madizhaksykeldi.crudshop.entity.entities.Client;
import kz.madizhaksykeldi.crudshop.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(
            summary = "Find all Clients",
            description = "Get all Clients objects.",
            tags = {"get"})
    @GetMapping("/getAll")
    @JsonView(ClientView.Get.class)
    public Collection<ClientDTO> findAll() {
        log.info("Looking for all clients");
        return clientService.findAll();
    }

    @Operation(
            summary = "Find a Client by Id",
            description = "Get a Client object by specifying its id.",
            tags = {"get"})
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(schema = @Schema(implementation = ClientDTO.class), mediaType = "application/json")})
    @ApiResponse(
            responseCode = "404",
            content = {@Content(schema = @Schema())})
    @ApiResponse(
            responseCode = "500",
            content = {@Content(schema = @Schema())})
    @GetMapping("/{id}")
    @JsonView(value = ClientView.Get.class)
    public ClientDTO findById(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id) {
        log.info("looking for clinet with id {}", id);
        return clientService.findById(id);
    }

    @Operation(
            summary = "Add a Client",
            description = "Create a Client object.",
            tags = {"post"})
    @PostMapping("/addClient")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = ClientView.Get.class)
    public ClientDTO create(
            @RequestBody @JsonView(value = ClientView.Post.class) ClientDTO clientDTO) {
        log.info("Creating client: {}", clientDTO.toString());
        return clientService.create(clientDTO);
    }

    @Operation(
            summary = "Update a Client by Id",
            description = "Update a Client object by specifying its id.",
            tags = {"put"})
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = ClientView.Get.class)
    public ClientDTO update(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id,
            @RequestBody @JsonView(value = ClientView.Put.class) ClientDTO clientDTO) {
        log.info("Update Client with id: {}", id);

        final Client givenClient = Client.builder()
                .clientId(id)
                .firstName(clientDTO.getFirstName())
                .lastName(clientDTO.getLastName())
                .email(clientDTO.getEmail())
                .build();

        return clientService.update(id, givenClient);
    }

    @Operation(
            summary = "Delete a Client by Id",
            description = "Delete a Client object by specifying its id.",
            tags = {"delete"})
    @DeleteMapping("/{id}")
    public Long deleteEmployee(
            @Parameter(description = "Client Id.", example = "1")
            @PathVariable Long id) {
        log.debug("Delete Client with id: {}", id);
        return clientService.deleteById(id);
    }
}

