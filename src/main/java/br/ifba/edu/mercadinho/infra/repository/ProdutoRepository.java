package br.ifba.edu.mercadinho.infra.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByCodigo(Integer codigo);
    Optional<Produto> findByCodigoAndId(Integer codigo, Integer id);
    List<Produto> findAllByNomeIgnoreCaseStartingWith(String search);
}
