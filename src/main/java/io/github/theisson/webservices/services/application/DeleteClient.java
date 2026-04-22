package io.github.theisson.webservices.services.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.theisson.webservices.exceptions.ResourceNotFoundException;
import io.github.theisson.webservices.repositories.ClientRepository;

@Service
public class DeleteClient {
    
    private final ClientRepository clientRepository;

    public DeleteClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void execute(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente não encontrado com id " + id);
        }

        clientRepository.deleteById(id);
    }
}
