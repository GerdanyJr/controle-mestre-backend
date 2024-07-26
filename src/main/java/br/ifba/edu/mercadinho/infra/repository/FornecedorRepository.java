package br.ifba.edu.mercadinho.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    Optional<Fornecedor> findByEmail(String email);

    Optional<Fornecedor> findByCnpj(String cnpj);

    Optional<Fornecedor> findByTelefone(String telefone);
}
