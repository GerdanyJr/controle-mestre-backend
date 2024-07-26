package br.ifba.edu.mercadinho.model.dto;

import java.util.Optional;

import br.ifba.edu.mercadinho.model.entities.Endereco;

public record FornecedorDto(
        String nome,
        String email,
        String cnpj,
        Optional<Endereco> endereco,
        String telefone) {

    public FornecedorDto(
            String nome,
            String email,
            String cnpj,
            String telefone) {
        this(nome, email, cnpj, Optional.empty(), telefone);
    }
}
