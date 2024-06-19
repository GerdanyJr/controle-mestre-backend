package br.ifba.edu.mercadinho.infra.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findAllByNomeStartingWith(String search);
}
