package io.github.theisson.webservices.services.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.exceptions.ResourceNotFoundException;
import io.github.theisson.webservices.models.entities.Client;
import io.github.theisson.webservices.repositories.ClientRepository;

@Service
public class GetClient {
    
    private final ClientRepository clientRepository;

    public GetClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO execute(Long id) {
        Client entity = clientRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        return new ClientResponseDTO(entity);
    }
}
