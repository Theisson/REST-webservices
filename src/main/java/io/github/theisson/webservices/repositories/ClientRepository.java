package io.github.theisson.webservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.theisson.webservices.models.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {}
