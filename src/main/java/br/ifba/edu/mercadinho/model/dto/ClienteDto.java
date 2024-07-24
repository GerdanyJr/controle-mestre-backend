package br.ifba.edu.mercadinho.model.dto;

import java.time.LocalDate;
import java.util.Optional;

import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.enums.Sexo;

public record ClienteDto(String nome,
                String cpf,
                Optional<Endereco> endereco,
                LocalDate dataNascimento,
                Sexo sexo) {

        public ClienteDto(String nome, String cpf, LocalDate dataNascimento, Sexo sexo) {
                this(nome, cpf, Optional.empty(), dataNascimento, sexo);
        }
}