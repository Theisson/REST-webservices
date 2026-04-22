package io.github.theisson.webservices.services.application;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import io.github.theisson.webservices.dto.ClientRequestDTO;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.exceptions.DatabaseException;
import io.github.theisson.webservices.models.entities.Client;
import io.github.theisson.webservices.repositories.ClientRepository;

@Service
public class CreateClient {
    
    private final ClientRepository clientRepository;

    public CreateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO execute(ClientRequestDTO dto) {
        try {
            Client entity = new Client(
                dto.name(),
                dto.cpf(),
                dto.income(),
                dto.birthDate(),
                dto.children()
            );

            entity = clientRepository.save(entity);

            return new ClientResponseDTO(entity);
        }
        catch(DataIntegrityViolationException e) {
            throw new DatabaseException("Já existe um cliente com o CPF: " + dto.cpf());
        }
    }
}
