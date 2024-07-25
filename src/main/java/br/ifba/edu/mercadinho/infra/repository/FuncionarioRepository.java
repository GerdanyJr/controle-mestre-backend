package br.ifba.edu.mercadinho.infra.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import br.ifba.edu.mercadinho.model.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findByNomeStartingWith(String nome);
    Optional<Funcionario> findByEmail(String email);
    Optional<Funcionario> findByTelefone(String telefone);
    Optional<Funcionario> findByCpf(String cpf);
}
