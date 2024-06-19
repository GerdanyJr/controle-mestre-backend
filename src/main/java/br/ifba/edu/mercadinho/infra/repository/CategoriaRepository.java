package br.ifba.edu.mercadinho.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
