package io.github.theisson.webservices.controllers;

import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.theisson.webservices.dto.ClientRequestDTO;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.services.application.CreateClient;
import io.github.theisson.webservices.services.application.DeleteClient;
import io.github.theisson.webservices.services.application.GetClient;
import io.github.theisson.webservices.services.application.ListClients;
import io.github.theisson.webservices.services.application.UpdateClient;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final GetClient getClient;
    private final ListClients listClients;
    private final CreateClient createClient;
    private final UpdateClient updateClient;
    private final DeleteClient deleteClient;

    public ClientController(
        GetClient getClient, 
        ListClients listClients, 
        CreateClient createClient, 
        UpdateClient updateClient, 
        DeleteClient deleteClient
    ) {
        this.getClient = getClient;
        this.listClients = listClients;
        this.createClient = createClient;
        this.updateClient = updateClient;
        this.deleteClient = deleteClient;
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

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO updatedClient = updateClient.execute(id, dto);
        
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteClient.execute(id);
        
        return ResponseEntity.noContent().build();
    }
}
