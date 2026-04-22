package io.github.theisson.webservices.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.services.application.GetClient;
import io.github.theisson.webservices.services.application.ListClients;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    private final GetClient getClient;
    private final ListClients listClients;

    public ClientController(GetClient getClient, ListClients listClients) {
        this.getClient = getClient;
        this.listClients = listClients;
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
}
