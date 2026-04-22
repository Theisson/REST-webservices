package io.github.theisson.webservices.controllers;

import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.theisson.webservices.dto.ClientRequestDTO;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.services.application.CreateClient;
import io.github.theisson.webservices.services.application.GetClient;
import io.github.theisson.webservices.services.application.ListClients;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final GetClient getClient;
    private final ListClients listClients;
    private final CreateClient createClient;

    public ClientController(GetClient getClient, ListClients listClients, CreateClient createClient) {
        this.getClient = getClient;
        this.listClients = listClients;
        this.createClient = createClient;
    }

    @GetMapping
    public ResponseEntity<Page<ClientResponseDTO>> findAll(Pageable pageable) {
        Page<ClientResponseDTO> list = listClients.execute(pageable);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        ClientResponseDTO client = getClient.execute(id);

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> insert(@Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO newClient = createClient.execute(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newClient.id())
                .toUri();

        return ResponseEntity.created(uri).body(newClient);
    }
}
