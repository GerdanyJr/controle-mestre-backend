package br.ifba.edu.mercadinho.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
