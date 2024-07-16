package br.ifba.edu.mercadinho.model.dto;

import java.time.LocalDate;

import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.enums.Sexo;

public record ClienteDto(String nome,
        String cpf,
        Endereco endereco,
        LocalDate dataNascimento,
        Sexo sexo) {

}
