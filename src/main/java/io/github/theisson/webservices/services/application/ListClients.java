package io.github.theisson.webservices.services.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.models.entities.Client;
import io.github.theisson.webservices.repositories.ClientRepository;

@Service
public class ListClients {
    
    private final ClientRepository clientRepository;

    public ListClients(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> execute(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);

        return result.map(ClientResponseDTO::new);
    }
}
