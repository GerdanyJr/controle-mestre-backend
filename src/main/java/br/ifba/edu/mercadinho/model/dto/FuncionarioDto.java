package br.ifba.edu.mercadinho.model.dto;

import java.util.Optional;

import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.enums.Cargo;

public record FuncionarioDto(
                String nome,
                String apelido,
                String cpf,
                String email,
                Optional<Endereco> endereco,
                String telefone,
                Cargo cargo) {

        public FuncionarioDto(
                        String nome,
                        String apelido,
                        String cpf,
                        String email,
                        String telefone,
                        Cargo cargo) {
                this(nome, apelido, cpf, email, Optional.empty(), telefone, cargo);
        }
}
