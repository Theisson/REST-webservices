package io.github.theisson.webservices.services.application;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.theisson.webservices.dto.ClientRequestDTO;
import io.github.theisson.webservices.dto.ClientResponseDTO;
import io.github.theisson.webservices.exceptions.DatabaseException;
import io.github.theisson.webservices.exceptions.ResourceNotFoundException;
import io.github.theisson.webservices.models.entities.Client;
import io.github.theisson.webservices.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UpdateClient {
    
    private final ClientRepository clientRepository;

    public UpdateClient(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ClientResponseDTO execute(Long id, ClientRequestDTO dto) {
        try {
            Client entity = clientRepository.getReferenceById(id);

            entity.updateData(
                dto.name(),
                dto.cpf(),
                dto.income(),
                dto.birthDate(),
                dto.children()
            );

            entity = clientRepository.saveAndFlush(entity);

            return new ClientResponseDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente não encontrado com id: " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Já existe um cliente com o CPF: " + dto.cpf());
        }
    }
}
