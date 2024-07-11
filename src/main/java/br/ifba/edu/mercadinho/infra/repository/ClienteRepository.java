package br.ifba.edu.mercadinho.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Optional<Cliente> findByCpf(String cpf);
}
