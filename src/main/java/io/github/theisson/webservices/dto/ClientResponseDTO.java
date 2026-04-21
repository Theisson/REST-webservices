package io.github.theisson.webservices.dto;

import java.time.LocalDate;
import io.github.theisson.webservices.models.entities.Client;

public record ClientResponseDTO(
    Long id,
    String name,
    String cpf,
    Double income,
    LocalDate birthDate,
    Integer children
) {
    
    public ClientResponseDTO(Client entity) {
        this(
            entity.getId(),
            entity.getName(),
            entity.getCpf(),
            entity.getIncome(),
            entity.getBirthDate(),
            entity.getChildren()
        );
    }
}
